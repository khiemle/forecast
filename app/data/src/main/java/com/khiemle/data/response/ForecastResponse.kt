package com.khiemle.data.response

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("day") val day: Double? = null,
    @SerializedName("night") val night: Double? = null,
    @SerializedName("eve") val eve: Double? = null,
    @SerializedName("morn") val morn: Double? = null,
)

data class WeatherResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class ForecastResponse(
    @SerializedName("dt") val dt: Long,
    @SerializedName("temp") val temp: TemperatureResponse,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("weather") val weather: List<WeatherResponse>
)