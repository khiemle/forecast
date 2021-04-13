package com.khiemle.domain.openweather.mapper

import android.text.format.DateFormat
import com.khiemle.data.response.OpenWeatherGetDailyResponse
import com.khiemle.data.response.TemperatureResponse
import com.khiemle.domain.openweather.entities.Forecast
import java.util.*

internal const val CELSIUS_CHAR = "\\u2109"
internal const val DESCRIPTION_SEPARATOR = ", "
internal const val HUMIDITY_SIGN = "%"

internal fun mapResponseDataToEntity(responseData: OpenWeatherGetDailyResponse): List<Forecast> {
    return responseData.list.map { forecastResponse ->
        Forecast(
            date = convertTimestampToDisplayDate(forecastResponse.dt * 1000),
            averageTemperature = convertTemperatureToDisplayTemperature(forecastResponse.temp),
            humidity = "${forecastResponse.humidity}$HUMIDITY_SIGN",
            pressure = "${forecastResponse.pressure}",
            description = forecastResponse.weather.joinToString(separator = DESCRIPTION_SEPARATOR) { weatherResponse ->
                weatherResponse.description
            }
        )
    }
}

internal fun convertTimestampToDisplayDate(timestamp: Long) : String {
    val cal: Calendar = Calendar.getInstance()
    cal.timeInMillis = timestamp
    return DateFormat.format("EEE, dd MMM yyyy", cal).toString()
}

internal fun convertTemperatureToDisplayTemperature(temperatureResponse: TemperatureResponse) : String {
    return "${(temperatureResponse.day + temperatureResponse.night) / 2}$CELSIUS_CHAR"
}