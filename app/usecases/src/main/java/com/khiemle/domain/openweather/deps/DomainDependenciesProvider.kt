package com.khiemle.domain.openweather.deps

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases

interface DomainDependenciesProvider {
    fun openWeatherUseCases(): IOpenWeatherUseCases
}