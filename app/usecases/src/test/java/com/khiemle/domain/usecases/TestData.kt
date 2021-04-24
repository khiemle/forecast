package com.khiemle.domain.usecases

import com.khiemle.data.response.*
import com.khiemle.domain.openweather.usecases.OpenWeatherUseCases

const val TIMESTAMP_TEST = 1618286400L
const val AVERAGE_TEMP = 30
const val PRESSURE = 1000
const val HUMIDITY = 40
const val ID = 1
const val EMPTY = ""
const val DESCRIPTION = "weather description"

internal fun getValidForeCastResponse(): OpenWeatherGetDailyResponse {
    return OpenWeatherGetDailyResponse(
        city = CityResponse(id = 1, name = "Saigon"),
        count = "${OpenWeatherUseCases.FIXED_COUNT}",
        list = mutableListOf<ForecastResponse>().apply {
            repeat(OpenWeatherUseCases.FIXED_COUNT) {
                this.add(
                    ForecastResponse(
                        dt = TIMESTAMP_TEST,
                        temp = TemperatureResponse(
                            min = AVERAGE_TEMP.toDouble(),
                            max = AVERAGE_TEMP.toDouble(),
                        ),
                        pressure = PRESSURE,
                        humidity = HUMIDITY,
                        weather = listOf(
                            WeatherResponse(
                                id = ID,
                                main = EMPTY,
                                description = DESCRIPTION,
                                icon = EMPTY
                            )
                        )
                    )
                )
            }
        }
    )
}