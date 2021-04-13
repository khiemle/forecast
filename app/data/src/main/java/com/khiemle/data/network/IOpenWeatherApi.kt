package com.khiemle.data.network

import com.khiemle.data.network.response.OpenWeatherGetDailyResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface IOpenWeatherApi {
    @GET("/data/2.5/forecast/daily")
    suspend fun getDaily(@Query("q") city: String,
                         @Query("cnt") days: Int,
                         @Query("appid") appId: String) : OpenWeatherGetDailyResponse
}