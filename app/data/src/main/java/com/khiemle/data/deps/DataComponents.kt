package com.khiemle.data.deps

import android.content.Context
import com.khiemle.data.repositories.IOpenWeather
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, OpenWeatherModule::class, DatabaseModule::class])
interface DataComponents: DataDependenciesProvider {

    override fun openWeatherRepository(): IOpenWeather

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        @BindsInstance
        fun appConfigs(appConfigs: AppConfigs): Builder

        fun build(): DataComponents
    }
}