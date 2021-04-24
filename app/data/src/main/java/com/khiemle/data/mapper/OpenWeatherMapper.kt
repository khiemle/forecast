package com.khiemle.data.mapper

import com.khiemle.data.remote.response.GetDailyApiResponse
import com.khiemle.data.remote.response.TemperatureResponse
import com.khiemle.data.repositories.OpenWeatherResult
import com.khiemle.data.repositories.OpenWeatherResultError
import com.khiemle.data.repositories.OpenWeatherResultSuccess
import com.khiemle.domain.models.*

internal const val CELSIUS_CHAR = "\u2103"
internal const val DESCRIPTION_SEPARATOR = ", "
internal const val HUMIDITY_SIGN = "%"

internal fun parseResult(result: OpenWeatherResult<GetDailyApiResponse>): DataResult<List<Forecast>> {
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

fun mapResponseDataToEntity(apiResponseData: GetDailyApiResponse): List<Forecast> {
    return apiResponseData.list.map { forecastResponse ->
        Forecast(
            timeStamp = forecastResponse.dt * 1000,
            averageTemperature = convertTemperatureToDisplayTemperature(forecastResponse.temp),
            humidity = "${forecastResponse.humidity}$HUMIDITY_SIGN",
            pressure = "${forecastResponse.pressure}",
            description = forecastResponse.weather.joinToString(separator = DESCRIPTION_SEPARATOR) { weatherResponse ->
                weatherResponse.description
            }
        )
    }
}

internal fun convertTemperatureToDisplayTemperature(temperatureResponse: TemperatureResponse) : String {
    return "${((temperatureResponse.min + temperatureResponse.max) / 2).toInt()}$CELSIUS_CHAR"
}