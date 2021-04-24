package com.khiemle.domain.models

sealed class DataResult<out T: Any>

class DataResultSuccess<out T: Any>(val data: T) : DataResult<T>()

open class DataResultError(
    val message: String? = null,
    val code: Int? = null
) : DataResult<Nothing>() {
    companion object {
        const val NOT_FOUND_CITY_CODE = 404
        const val NETWORK_ERROR_MESSAGE = "Please check your network connection"
        const val CITY_NOT_FOUND_MESSAGE = "Please enter right searching city name and try again"
        const val COMMON_ERROR_MESSAGE = "Something went wrong"
    }
}

class NetworkError(code: Int? = null) : DataResultError(message = NETWORK_ERROR_MESSAGE, code = code)
class CityNotFoundError(code: Int? = null): DataResultError(message = CITY_NOT_FOUND_MESSAGE, code = code)
class CommonError(code: Int? = null): DataResultError(message = COMMON_ERROR_MESSAGE, code = code)

