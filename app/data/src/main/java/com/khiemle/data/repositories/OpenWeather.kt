package com.khiemle.data.repositories

import com.khiemle.data.deps.OPEN_WEATHER_APP_ID
import com.khiemle.data.mapper.parseResult
import com.khiemle.data.remote.IOpenWeatherApi
import com.khiemle.data.remote.response.GetDailyApiResponse
import com.khiemle.data.room.ForecastDatabase
import com.khiemle.data.room.models.CityKeyMap
import com.khiemle.data.room.models.mapToForecast
import com.khiemle.data.room.models.mapToPersistentForecast
import com.khiemle.domain.models.DataResult
import com.khiemle.domain.models.DataResultSuccess
import com.khiemle.domain.models.Forecast
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Named

interface IOpenWeather {
    suspend fun getDaily(
        cityName: String,
        numberDayOfForecast: Int,
        units: String
    ): OpenWeatherResult<GetDailyApiResponse>

    suspend fun getDailyV2(
        cityName: String,
        numberDayOfForecast: Int,
        units: String,
        timestamp: Long
    ): Flow<DataResult<List<Forecast>>>

    fun getDailyLocal(
        cityName: String,
        numberDayOfForecast: Int,
        timestamp: Long
    ): List<Forecast>?
}

internal class OpenWeather @Inject constructor(
    private val api: IOpenWeatherApi,
    private val database: ForecastDatabase,
    @Named(OPEN_WEATHER_APP_ID) val appId: String
) : IOpenWeather {

    override fun getDailyLocal(
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

    override suspend fun getDaily(
        cityName: String,
        numberDayOfForecast: Int,
        units: String
    ): OpenWeatherResult<GetDailyApiResponse> {
        return runNetworkSafe {
            return OpenWeatherResultSuccess(
                api.getDaily(
                    city = cityName,
                    days = numberDayOfForecast,
                    appId = appId,
                    units = units
                )
            )
        }
    }

    override suspend fun getDailyV2(
        cityName: String,
        numberDayOfForecast: Int,
        units: String,
        timestamp: Long
    ): Flow<DataResult<List<Forecast>>> = flow {
        val cacheForecast = getDailyLocal(
            cityName = cityName,
            numberDayOfForecast = numberDayOfForecast,
            timestamp = timestamp
        )
        val shouldLoadRemoteData = cacheForecast == null
        cacheForecast?.let {
            emit(DataResultSuccess(data = it))
        }
        if (shouldLoadRemoteData) {
            val listRemoteApiResponse: OpenWeatherResult<GetDailyApiResponse> = getDaily(
                cityName = cityName,
                numberDayOfForecast = numberDayOfForecast,
                units = units
            )

            if (listRemoteApiResponse is OpenWeatherResultSuccess) {
                val cacheList =
                    listRemoteApiResponse.data.list.map { it.mapToPersistentForecast(listRemoteApiResponse.data.city.id) }
                database.forecastRDao().insertForecasts(cacheList)
                database.cityKeyMapDao().insertCityKeyMap(
                    CityKeyMap(
                        cityId = cacheList.first().cityId,
                        query = cityName,
                        timestamp = timestamp
                    )
                )
            }
            emit(parseResult(listRemoteApiResponse))
        }

    }
}