package com.khiemle.usecases.openweather.deps

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.usecases.openweather.usecases.IOpenWeatherUseCases
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UseCasesModule::class])
interface UseCasesComponents: UseCasesDependenciesProvider {
    override fun openWeatherUseCases(): IOpenWeatherUseCases

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun openWeatherRepository(openWeatherRepository: IOpenWeather): Builder

        fun build(): UseCasesComponents
    }
}