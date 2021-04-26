package com.khiemle.nab.framework.room.models

import androidx.room.Entity
import com.khiemle.usecases.models.Forecast

internal const val CELSIUS_CHAR = "\u2103"
internal const val HUMIDITY_SIGN = "%"
internal const val EMPTY = ""

@Entity(tableName = "forecast", primaryKeys = ["cityId", "timeStamp"])
data class PersistentForecast(
    val cityId: Long = 0L,
    val timeStamp: Long = 0L,
    val averageTemperature: Double,
    val pressure: Int,
    val humidity: Int,
    val description: String
)

fun PersistentForecast.mapToForecast(): Forecast {
    return Forecast(
        timeStamp = timeStamp,
        averageTemperature = "${averageTemperature.toInt()}$CELSIUS_CHAR",
        humidity = "$humidity$HUMIDITY_SIGN",
        pressure = pressure.toString(),
        description = description
    )
}

fun Forecast.mapToPersistentForecast(cityId: Long): PersistentForecast {
    return PersistentForecast(
        cityId = cityId,
        timeStamp = timeStamp,
        averageTemperature = averageTemperature.replace(CELSIUS_CHAR, EMPTY).toDouble(),
        humidity = humidity.replace(HUMIDITY_SIGN, EMPTY).toInt(),
        pressure = pressure.toInt(),
        description = description
    )
}