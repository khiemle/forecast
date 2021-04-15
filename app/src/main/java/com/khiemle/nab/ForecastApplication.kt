package com.khiemle.nab

import android.app.Application
import com.khiemle.data.deps.DaggerDataComponents
import com.khiemle.data.deps.DataComponents
import com.khiemle.domain.openweather.deps.DaggerDomainComponents
import com.khiemle.domain.openweather.deps.DomainComponents
import com.khiemle.nab.deps.AppComponents
import com.khiemle.nab.deps.DaggerAppComponents
import com.khiemle.nab.deps.DependenciesProvider
import com.khiemle.nab.deps.InjectionProvider

class ForecastApplication: Application(), DependenciesProvider {

    private val dataComponents: DataComponents by lazy {
        DaggerDataComponents.builder().build()
    }
    private val domainComponents: DomainComponents by lazy {
        DaggerDomainComponents.builder().openWeatherRepository(dataComponents.openWeatherRepository()).build()
    }

    private val appComponents: AppComponents by lazy {
        DaggerAppComponents.builder().openWeatherUseCases(domainComponents.openWeatherUseCases()).build()
    }

    override fun provideInjectionProvider(): InjectionProvider = appComponents

}