package com.khiemle.data.repositories
import android.util.Log
import com.khiemle.data.network.IOpenWeatherApi
import com.khiemle.data.response.OpenWeatherGetDailyResponse
import javax.inject.Inject

interface IOpenWeather {
    suspend fun getDaily(
        cityName: String,
        numberDayOfForecast: Int,
        apiKey: String
    ): OpenWeatherResult<OpenWeatherGetDailyResponse>
}

internal class OpenWeather @Inject constructor(
    private val api: IOpenWeatherApi
): IOpenWeather {
    override suspend fun getDaily(
        cityName: String,
        numberDayOfForecast: Int,
        apiKey: String
    ): OpenWeatherResult<OpenWeatherGetDailyResponse> {
        return runNetworkSafe {
            OpenWeatherResultSuccess(api.getDaily(
                city = cityName,
                days = numberDayOfForecast,
                appId = apiKey
            ))
        }
    }
}