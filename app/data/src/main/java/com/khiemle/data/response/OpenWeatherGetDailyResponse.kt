package com.khiemle.data.response

import com.google.gson.annotations.SerializedName

data class OpenWeatherGetDailyResponse(
    @SerializedName("cnt") val count: String,
    @SerializedName("city") val city: CityResponse,
    @SerializedName("list") val list: List<ForecastResponse>
)