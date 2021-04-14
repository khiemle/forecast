package com.khiemle.domain.openweather.deps

import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import com.khiemle.domain.openweather.usecases.OpenWeatherUseCases
import dagger.Binds
import dagger.Module

@Module
internal abstract class DomainUseCasesModule {
    @Binds
    abstract fun bindOpenWeatherUseCases(o: OpenWeatherUseCases): IOpenWeatherUseCases
}