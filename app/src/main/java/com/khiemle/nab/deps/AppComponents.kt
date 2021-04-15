package com.khiemle.nab.deps

import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelsModule::class])
interface AppComponents: InjectionProvider {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun openWeatherUseCases(openWeatherUseCases: IOpenWeatherUseCases): Builder
        fun build(): AppComponents
    }
}