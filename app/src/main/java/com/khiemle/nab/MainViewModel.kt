package com.khiemle.nab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khiemle.domain.openweather.entities.DataResultError
import com.khiemle.domain.openweather.entities.DataResultSuccess
import com.khiemle.domain.openweather.entities.Forecast
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val openWeatherUseCases: IOpenWeatherUseCases
) : ViewModel() {
    private val _mainState = MutableLiveData<MainState>()
    val mainState: LiveData<MainState>
        get() = Transformations.distinctUntilChanged(_mainState)

    fun search(query: String) {
        viewModelScope.launch {
            _mainState.postValue(MainState.ShowLoading)
            val result = openWeatherUseCases.getDailyForecast(query)
            if (result is DataResultSuccess) {
                _mainState.postValue(MainState.Forecasts(items = result.data))
            } else {
                _mainState.postValue(MainState.ShowErrorMessage(
                    msg = (result as DataResultError).message.orEmpty()
                ))
            }
        }
    }
}

sealed class MainState {
    class Forecasts(
        val items: List<Forecast>
    ) : MainState()
    object ShowLoading: MainState()
    class ShowErrorMessage(val msg: String) : MainState()
}