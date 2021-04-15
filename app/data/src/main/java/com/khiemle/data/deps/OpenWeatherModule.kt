package com.khiemle.data.deps

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.data.repositories.OpenWeather
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
internal abstract class OpenWeatherModule {
    @Binds
    @Singleton
    abstract fun bindOpenWeatherRepository(o: OpenWeather) : IOpenWeather
}