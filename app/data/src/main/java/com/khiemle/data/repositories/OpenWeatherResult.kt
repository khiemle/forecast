package com.khiemle.data.repositories

import java.io.IOException
import java.net.UnknownHostException

sealed class OpenWeatherResult<out T: Any>

class OpenWeatherResultSuccess<out T: Any>(val data: T) : OpenWeatherResult<T>()

open class OpenWeatherResultError(
    val exception: Throwable,
    val message: String? = null,
    val code: Int? = null
): OpenWeatherResult<Nothing>() {

    fun isDueToNetwork(): Boolean {
        return exception is IOException || exception is UnknownHostException
    }

    fun isErrorMessageValid(): Boolean {
        return message.isNullOrEmpty().not()
    }
}