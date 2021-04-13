package com.khiemle.data.response

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    @SerializedName("day") val day: Double,
    @SerializedName("min") val min: Double,
    @SerializedName("max") val max: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("eve") val eve: Double,
    @SerializedName("morn") val morn: Double,
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