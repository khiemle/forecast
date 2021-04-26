package com.khiemle.usecases.openweather.deps

import com.khiemle.usecases.openweather.usecases.IOpenWeatherUseCases

interface UseCasesDependenciesProvider {
    fun openWeatherUseCases(): IOpenWeatherUseCases
}