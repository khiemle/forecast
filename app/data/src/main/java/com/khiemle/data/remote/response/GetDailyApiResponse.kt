package com.khiemle.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetDailyApiResponse(
    @SerializedName("cnt") val count: String,
    @SerializedName("city") val city: CityResponse,
    @SerializedName("list") val list: List<ForecastResponse>
)