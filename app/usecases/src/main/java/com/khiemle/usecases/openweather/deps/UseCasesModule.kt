package com.khiemle.usecases.openweather.deps

import com.khiemle.usecases.openweather.usecases.IOpenWeatherUseCases
import com.khiemle.usecases.openweather.usecases.OpenWeatherUseCases
import dagger.Binds
import dagger.Module

@Module
internal abstract class UseCasesModule {
    @Binds
    abstract fun bindOpenWeatherUseCases(o: OpenWeatherUseCases): IOpenWeatherUseCases
}