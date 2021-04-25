package com.khiemle.domain.usecases

import com.khiemle.data.remote.response.*
import com.khiemle.domain.models.Forecast
import com.khiemle.domain.openweather.usecases.OpenWeatherUseCases

const val TIMESTAMP_TEST = 1618286400L
const val AVERAGE_TEMP = 30
const val PRESSURE = 1000
const val HUMIDITY = 40
const val DESCRIPTION = "weather description"

internal fun getValidForeCastResponse(): List<Forecast> {
    return mutableListOf<Forecast>().apply {
            repeat(OpenWeatherUseCases.FIXED_COUNT) {
                this.add(
                    Forecast(
                        timeStamp = TIMESTAMP_TEST,
                        averageTemperature = AVERAGE_TEMP.toString(),
                        pressure = PRESSURE.toString(),
                        humidity = HUMIDITY.toString(),
                        description = DESCRIPTION
                    )
                )
            }
        }
}