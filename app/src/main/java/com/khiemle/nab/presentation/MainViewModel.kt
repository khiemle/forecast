package com.khiemle.nab.presentation

import androidx.lifecycle.*
import com.khiemle.usecases.models.DataResultError
import com.khiemle.usecases.models.DataResultSuccess
import com.khiemle.usecases.models.Forecast
import com.khiemle.usecases.openweather.usecases.IOpenWeatherUseCases
import com.khiemle.nab.deps.IO_DISPATCHER
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject
import javax.inject.Named

class MainViewModel @Inject constructor(
    private val openWeatherUseCases: IOpenWeatherUseCases,
    @Named(IO_DISPATCHER) private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _mainState = MutableLiveData<MainState>()
    val mainState: LiveData<MainState>
        get() = Transformations.distinctUntilChanged(_mainState)

    private fun postValue(state: MainState) {
        _mainState.postValue(state)
    }

    private var searchJob: Job? = null

    fun searchV2(query: String, timestamp: Long) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_DURATION)
            postValue(MainState.ShowLoading)
            withContext(dispatcher) {
                openWeatherUseCases.getDailyForecastV2(query, timestamp = timestamp)
                    .collect { result ->
                        if (result is DataResultSuccess) {
                            postValue(MainState.Forecasts(items = result.data))
                        } else {
                            postValue(
                                MainState.ShowErrorMessage(
                                    msg = (result as DataResultError).message.orEmpty()
                                )
                            )
                        }
                    }
            }

        }
    }

    fun clearSearchText() {
        searchJob?.cancel()
        postValue(MainState.Forecasts(items = listOf()))
    }

    companion object {
        const val DEBOUNCE_DURATION = 1000L
    }
}

sealed class MainState {
    class Forecasts(
        val items: List<Forecast>
    ) : MainState()

    object ShowLoading : MainState()
    class ShowErrorMessage(val msg: String) : MainState()
}