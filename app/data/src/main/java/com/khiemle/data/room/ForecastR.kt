package com.khiemle.data.room

import androidx.room.Entity


@Entity(tableName = "forecast", primaryKeys = ["cityId", "timeStamp"])
data class ForecastR(
    val cityId: Long = 0L,
    val timeStamp: Long = 0L,
    val averageTemperature: String? = null,
    val pressure: String? = null,
    val humidity: String? = null,
    val description: String? = null
)