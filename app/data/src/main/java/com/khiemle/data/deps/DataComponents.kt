package com.khiemle.data.deps

import com.khiemle.data.repositories.IOpenWeather
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, OpenWeatherModule::class])
interface DataComponents: DataDependenciesProvider {

    override fun openWeatherRepository(): IOpenWeather

    @Component.Builder
    interface Builder {
        fun build(): DataComponents
    }
}