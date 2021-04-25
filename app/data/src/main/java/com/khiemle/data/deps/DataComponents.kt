package com.khiemle.data.deps

import android.content.Context
import com.khiemle.data.local.IOpenWeatherLocal
import com.khiemle.data.repositories.IOpenWeather
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, OpenWeatherModule::class])
interface DataComponents: DataDependenciesProvider {

    override fun openWeatherRepository(): IOpenWeather

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun appConfigs(appConfigs: AppConfigs): Builder

        @BindsInstance
        fun openWeatherLocalDatabase(localDatabase: IOpenWeatherLocal?): Builder

        fun build(): DataComponents
    }
}