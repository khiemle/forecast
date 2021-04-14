package com.khiemle.domain.openweather.deps

import com.khiemle.data.repositories.IOpenWeather
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DomainUseCasesModule::class])
interface DomainComponents {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun openWeatherRepository(openWeatherRepository: IOpenWeather): Builder

        fun build(): DomainComponents
    }
}