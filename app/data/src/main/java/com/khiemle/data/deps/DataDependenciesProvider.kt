package com.khiemle.data.deps

import com.khiemle.data.repositories.IOpenWeather

interface DataDependenciesProvider {
    fun openWeatherRepository(): IOpenWeather
}