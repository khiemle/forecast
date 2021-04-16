package com.khiemle.data.room.models

import androidx.room.Entity
import com.khiemle.data.response.ForecastResponse
import com.khiemle.data.response.TemperatureResponse
import com.khiemle.data.response.WeatherResponse


@Entity(tableName = "forecast", primaryKeys = ["cityId", "timeStamp"])
data class PersistentForecast(
    val cityId: Long = 0L,
    val timeStamp: Long = 0L,
    val averageTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val description: String
)


fun PersistentForecast.mapToForecastResponse() : ForecastResponse {
    return ForecastResponse(
        dt = timeStamp,
        temp = TemperatureResponse(min = averageTemperature, max = averageTemperature),
        pressure = pressure,
        humidity = humidity,
        weather = listOf(WeatherResponse(
            description = description
        ))
    )
}

fun ForecastResponse.mapToPersistentForecast(cityId: Long): PersistentForecast {
    return PersistentForecast(
        cityId = cityId,
        timeStamp = dt,
        averageTemperature = (temp.min + temp.max) / 2,
        humidity = humidity,
        pressure = pressure,
        description = weather.joinToString() { weatherResponse ->
            weatherResponse.description
        }
    )
}