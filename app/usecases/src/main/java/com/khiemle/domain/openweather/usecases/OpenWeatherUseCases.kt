package com.khiemle.domain.openweather.usecases

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.domain.models.DataResult
import com.khiemle.domain.models.Forecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface IOpenWeatherUseCases {
    suspend fun getDailyForecastV2(query: String, timestamp: Long): Flow<DataResult<List<Forecast>>>
}

internal class OpenWeatherUseCases @Inject constructor(private val openWeather: IOpenWeather) :
    IOpenWeatherUseCases {

    override suspend fun getDailyForecastV2(
        query: String,
        timestamp: Long
    ): Flow<DataResult<List<Forecast>>> = flow {
        openWeather.getDailyV2(
            cityName = query,
            numberDayOfForecast = FIXED_COUNT,
            units = FIXED_UNITS,
            timestamp = timestamp
        ).collect { result ->
            emit(result)
        }
    }

    companion object {
        const val FIXED_COUNT = 7
        const val FIXED_UNITS = "metric"
    }
}



