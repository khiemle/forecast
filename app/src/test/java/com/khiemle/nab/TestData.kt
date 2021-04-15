package com.khiemle.nab

import com.khiemle.domain.openweather.entities.Forecast

const val TIMESTAMP_TEST = 1618286400L
const val AVERAGE_TEMP = 30
const val PRESSURE = 1000
const val HUMIDITY = 40
const val DESCRIPTION = "weather description"
const val FIXED_COUNT = 7

internal fun getValidForeCast(): List<Forecast> {
    return mutableListOf<Forecast>().apply {
            repeat(FIXED_COUNT) {
                this.add(
                    Forecast(
                        timeStamp =  TIMESTAMP_TEST * 1000,
                        averageTemperature = "$AVERAGE_TEMP\\u2103",
                        pressure = PRESSURE.toString(),
                        humidity = "$HUMIDITY%",
                        description = DESCRIPTION
                    )
                )
            }
        }

}