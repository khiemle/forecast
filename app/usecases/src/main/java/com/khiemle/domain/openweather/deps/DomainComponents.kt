package com.khiemle.domain.openweather.deps

import com.khiemle.data.repositories.IOpenWeather
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainUseCasesModule::class])
interface DomainComponents: DomainDependenciesProvider {
    override fun openWeatherUseCases(): IOpenWeatherUseCases

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun openWeatherRepository(openWeatherRepository: IOpenWeather): Builder

        fun build(): DomainComponents
    }
}