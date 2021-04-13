package com.khiemle.data.repositories

internal inline fun <T : Any> runNetworkSafe(block: () -> OpenWeatherResult<T>): OpenWeatherResult<T> {
    runCatching {
        return block.invoke()
    }.onFailure {
        return OpenWeatherErrorParser(it).parse()
    }
    return OpenWeatherResultError(ExceptionInInitializerError())
}