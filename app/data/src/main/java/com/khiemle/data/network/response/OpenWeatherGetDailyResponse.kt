package com.khiemle.data.network.response

import com.google.gson.annotations.SerializedName

data class OpenWeatherGetDailyResponse(
    @SerializedName("cnt") val count: String,
    @SerializedName("list") val list: List<ForecastResponse>
)