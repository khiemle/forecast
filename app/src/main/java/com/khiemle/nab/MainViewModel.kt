package com.khiemle.nab

import androidx.lifecycle.*
import com.khiemle.domain.openweather.entities.DataResultError
import com.khiemle.domain.openweather.entities.DataResultSuccess
import com.khiemle.domain.openweather.entities.Forecast
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val openWeatherUseCases: IOpenWeatherUseCases,
) : ViewModel() {
    private val _mainState = MutableLiveData<MainState>()
    val mainState: LiveData<MainState>
        get() = Transformations.distinctUntilChanged(_mainState)

    private fun postValue(state: MainState) {
        _mainState.postValue(state)
    }
    private var searchJob: Job? = null

    fun search(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(DEBOUNCE_DURATION)
            postValue(MainState.ShowLoading)
            val result = openWeatherUseCases.getDailyForecast(query)
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