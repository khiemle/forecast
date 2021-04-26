package com.khiemle.data.local

import com.khiemle.usecases.models.Forecast

interface IOpenWeatherLocal {
    fun getForecastDailyLocal(
        cityName: String,
        numberDayOfForecast: Int,
        timestamp: Long
    ): List<Forecast>?

    fun saveForecastDailyLocal(cityId: Long, query: String, timestamp: Long,forecasts: List<Forecast>)
}