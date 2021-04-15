package com.khiemle.nab.deps

import android.content.Context
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
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