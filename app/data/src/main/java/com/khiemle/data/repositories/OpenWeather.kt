package com.khiemle.data.repositories

import com.khiemle.data.network.IOpenWeatherApi
import com.khiemle.data.response.CityResponse
import com.khiemle.data.response.OpenWeatherGetDailyResponse
import com.khiemle.data.room.ForecastDatabase
import com.khiemle.data.room.models.CityKeyMap
import com.khiemle.data.room.models.mapToForecastR
import com.khiemle.data.room.models.mapToForecastResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface IOpenWeather {
    suspend fun getDaily(
        cityName: String,
        numberDayOfForecast: Int,
        apiKey: String,
        units: String
    ): OpenWeatherResult<OpenWeatherGetDailyResponse>

    suspend fun getDailyV2(
        cityName: String,
        numberDayOfForecast: Int,
        apiKey: String,
        units: String,
        timestamp: Long
    ): Flow<OpenWeatherResult<OpenWeatherGetDailyResponse>>
}

internal class OpenWeather @Inject constructor(
    private val api: IOpenWeatherApi,
    private val database: ForecastDatabase
) : IOpenWeather {

    private fun getDailyLocal(
        cityName: String,
        numberDayOfForecast: Int,
        timestamp: Long
    ): OpenWeatherResult<OpenWeatherGetDailyResponse>? {
        val cacheForecast = database.forecastRDao().loadAllForecasts(
            searchKey = cityName,
            timestamp = timestamp,
            count = numberDayOfForecast
        )
        if (cacheForecast.isNullOrEmpty().not() && cacheForecast.size == numberDayOfForecast) {
            val listResponse = cacheForecast.map { it.mapToForecastResponse() }
            return OpenWeatherResultSuccess(
                OpenWeatherGetDailyResponse(
                    city = CityResponse(id = cacheForecast.first().cityId),
                    count = numberDayOfForecast.toString(),
                    list = listResponse
                )
            )
        }
        return null
    }

    override suspend fun getDaily(
        cityName: String,
        numberDayOfForecast: Int,
        apiKey: String,
        units: String
    ): OpenWeatherResult<OpenWeatherGetDailyResponse> {
        return runNetworkSafe {
            return OpenWeatherResultSuccess(
                api.getDaily(
                    city = cityName,
                    days = numberDayOfForecast,
                    appId = apiKey,
                    units = units
                )
            )
        }
    }

    override suspend fun getDailyV2(
        cityName: String,
        numberDayOfForecast: Int,
        apiKey: String,
        units: String,
        timestamp: Long
    ): Flow<OpenWeatherResult<OpenWeatherGetDailyResponse>> = flow {
        val cacheForecast = getDailyLocal(
            cityName = cityName,
            numberDayOfForecast = numberDayOfForecast,
            timestamp = timestamp
        )
        val shouldLoadRemoteData = cacheForecast == null
        cacheForecast?.let {
            emit(cacheForecast)
        }
        if (shouldLoadRemoteData) {
            val listRemoteResponse: OpenWeatherResult<OpenWeatherGetDailyResponse> = getDaily(
                cityName = cityName,
                numberDayOfForecast = numberDayOfForecast,
                apiKey = apiKey,
                units = units
            )

            if (listRemoteResponse is OpenWeatherResultSuccess) {
                val cacheList =
                    listRemoteResponse.data.list.map { it.mapToForecastR(listRemoteResponse.data.city.id) }
                database.forecastRDao().insertForecasts(cacheList)
                database.cityKeyMapDao().insertCityKeyMap(
                    CityKeyMap(
                        cityId = cacheList.first().cityId,
                        query = cityName,
                        timestamp = timestamp
                    )
                )
            }
            emit(listRemoteResponse)
        }

    }
}