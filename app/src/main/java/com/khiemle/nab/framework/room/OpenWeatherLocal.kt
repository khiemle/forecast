package com.khiemle.nab.framework.room

import com.khiemle.data.local.IOpenWeatherLocal
import com.khiemle.domain.models.Forecast
import com.khiemle.nab.framework.room.models.CityKeyMap
import com.khiemle.nab.framework.room.models.mapToForecast
import com.khiemle.nab.framework.room.models.mapToPersistentForecast
import javax.inject.Inject

class OpenWeatherLocal @Inject constructor(private val database: ForecastDatabase) :
    IOpenWeatherLocal {
    override fun getForecastDailyLocal(
        cityName: String,
        numberDayOfForecast: Int,
        timestamp: Long
    ): List<Forecast>? {
        val cacheForecast = database.forecastRDao().loadAllForecasts(
            searchKey = cityName,
            timestamp = timestamp,
            count = numberDayOfForecast
        )
        if (cacheForecast.isNullOrEmpty().not() && cacheForecast.size == numberDayOfForecast) {
            return cacheForecast.map { it.mapToForecast() }
        }
        return null
    }

    override fun saveForecastDailyLocal(
        cityId: Long,
        query: String,
        timestamp: Long,
        forecasts: List<Forecast>
    ) {
        database.forecastRDao().insertForecasts(forecasts.map { it.mapToPersistentForecast(cityId = cityId) })
        database.cityKeyMapDao().insertCityKeyMap(
            CityKeyMap(
                cityId = cityId,
                query = query,
                timestamp = timestamp
            )
        )
    }
}