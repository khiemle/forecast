package com.khiemle.domain.openweather.entities

data class Forecast(
    val timeStamp: Long,
    val averageTemperature: String,
    val pressure: String,
    val humidity: String,
    val description: String
)