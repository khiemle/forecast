package com.khiemle.data.repositories

import retrofit2.HttpException

internal class OpenWeatherErrorParser(private val exception: Throwable) : Throwable(exception){
    fun parse(): OpenWeatherResult<Nothing> {
        if (exception is HttpException) {
            return OpenWeatherResultError(
                exception = exception,
                message = exception.message().orEmpty(),
                code = exception.code()
            )
        }
        return OpenWeatherResultError(exception)
    }
}