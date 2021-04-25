package com.khiemle.nab.deps

import android.content.Context
import com.khiemle.data.local.IOpenWeatherLocal
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import com.khiemle.nab.framework.room.DatabaseModule
import com.khiemle.nab.framework.room.LocalDataSourceModule
import com.khiemle.nab.framework.room.deps.FrameworkDependenciesProvider
import com.khiemle.nab.presentation.deps.ViewModelsModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelsModule::class, CommonModule::class])
interface AppComponents: InjectionProvider {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun openWeatherUseCases(openWeatherUseCases: IOpenWeatherUseCases): Builder
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponents
    }
}