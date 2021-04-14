package com.khiemle.data.deps

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.data.repositories.OpenWeather
import dagger.Binds
import dagger.Module


@Module
internal abstract class OpenWeatherModule {
    @Binds
    abstract fun bindOpenWeatherRepository(o: OpenWeather) : IOpenWeather
}