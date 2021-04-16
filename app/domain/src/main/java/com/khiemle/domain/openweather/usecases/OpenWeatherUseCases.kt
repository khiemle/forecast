package com.khiemle.domain.openweather.usecases

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.data.repositories.OpenWeatherResult
import com.khiemle.data.repositories.OpenWeatherResultError
import com.khiemle.data.repositories.OpenWeatherResultSuccess
import com.khiemle.data.response.OpenWeatherGetDailyResponse
import com.khiemle.domain.openweather.entities.*
import com.khiemle.domain.openweather.mapper.mapResponseDataToEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface IOpenWeatherUseCases {
    suspend fun getDailyForecast(query: String): DataResult<List<Forecast>>
    suspend fun getDailyForecastV2(query: String, timestamp: Long): Flow<DataResult<List<Forecast>>>
}

internal class OpenWeatherUseCases @Inject constructor (private val openWeather: IOpenWeather) : IOpenWeatherUseCases {

    private fun parseResult(result: OpenWeatherResult<OpenWeatherGetDailyResponse>): DataResult<List<Forecast>>  {
        if (result is OpenWeatherResultError) {
            if (result.isDueToNetwork()) {
                return NetworkError()
            } else {
                result.code?.let {
                    if (it == DataResultError.NOT_FOUND_CITY_CODE) {
                        return CityNotFoundError(code = result.code)
                    }
                }
                return CommonError()
            }
        }
        return DataResultSuccess(mapResponseDataToEntity((result as OpenWeatherResultSuccess).data))
    }
    override suspend fun getDailyForecast(query: String): DataResult<List<Forecast>> {
        val result = openWeather.getDaily(
            cityName = query,
            numberDayOfForecast = FIXED_COUNT,
            apiKey = TEMP_HARD_CODE_API_KEY,
            units = FIXED_UNITS
        )
        return parseResult(result)
    }

    override suspend fun getDailyForecastV2(query: String, timestamp: Long): Flow<DataResult<List<Forecast>>> = flow {
        openWeather.getDailyV2(
            cityName = query,
            numberDayOfForecast = FIXED_COUNT,
            apiKey = TEMP_HARD_CODE_API_KEY,
            units = FIXED_UNITS,
            timestamp = timestamp
        ).collect { result ->
            emit(parseResult(result))
        }
    }

    companion object {
        const val FIXED_COUNT = 7
        const val TEMP_HARD_CODE_API_KEY = "60c6fbeb4b93ac653c492ba806fc346d"
        const val FIXED_UNITS = "metric"
    }
}



