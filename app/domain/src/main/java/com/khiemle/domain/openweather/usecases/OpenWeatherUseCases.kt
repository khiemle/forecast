package com.khiemle.domain.openweather.usecases

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.data.repositories.OpenWeatherResultError
import com.khiemle.data.repositories.OpenWeatherResultSuccess
import com.khiemle.domain.openweather.entities.*
import com.khiemle.domain.openweather.mapper.mapResponseDataToEntity
import java.lang.Exception
import javax.inject.Inject

interface IOpenWeatherUseCases {
    suspend fun getDailyForecast(query: String): DataResult<List<Forecast>>
}

internal class OpenWeatherUseCases @Inject constructor (private val openWeather: IOpenWeather) : IOpenWeatherUseCases {
    override suspend fun getDailyForecast(query: String): DataResult<List<Forecast>> {
        val result = openWeather.getDaily(
            cityName = query,
            numberDayOfForecast = FIXED_COUNT,
            apiKey = TEMP_HARD_CODE_API_KEY,
            units = FIXED_UNITS
        )
        if (result is OpenWeatherResultError) {
            if (result.isDueToNetwork()) {
                return NetworkError()
            } else {
                result.code?.let {
                    if (it == DataResultError.NOT_FOUND_CITY_CODE) {
                        return CityNotFoundError()
                    }
                }
                return CommonError()
            }
        } else if (result is OpenWeatherResultSuccess) {
            return DataResultSuccess(mapResponseDataToEntity(result.data))
        }
        throw Exception()
    }

    companion object {
        const val FIXED_COUNT = 7
        const val TEMP_HARD_CODE_API_KEY = "60c6fbeb4b93ac653c492ba806fc346d"
        const val FIXED_UNITS = "metric"
    }
}



