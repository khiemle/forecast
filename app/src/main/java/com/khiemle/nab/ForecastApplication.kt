package com.khiemle.nab

import android.app.Application
import com.khiemle.data.deps.AppConfigs
import com.khiemle.data.deps.DaggerDataComponents
import com.khiemle.data.deps.DataComponents
import com.khiemle.domain.openweather.deps.DaggerDomainComponents
import com.khiemle.domain.openweather.deps.DomainComponents
import com.khiemle.nab.deps.AppComponents
import com.khiemle.nab.deps.DaggerAppComponents
import com.khiemle.nab.deps.DependenciesProvider
import com.khiemle.nab.deps.InjectionProvider
import com.khiemle.nab.framework.room.deps.DaggerFrameworkComponents
import com.khiemle.nab.framework.room.deps.FrameworkComponents

class ForecastApplication : Application(), DependenciesProvider {

    private val frameworkComponents: FrameworkComponents by lazy {
        DaggerFrameworkComponents.builder().context(applicationContext).build()
    }

    private val dataComponents: DataComponents by lazy {
        val appConfigs = AppConfigs(
            baseUrl = applicationContext.getString(R.string.base_url),
            appId = applicationContext.getString(R.string.app_id),
            openWeatherDomain = applicationContext.getString(R.string.open_weather_pattern),
            pinCertSHA = applicationContext.getString(R.string.pin_cert),
            pinCertSHABackupA = applicationContext.getString(R.string.pin_cert_back_up_a),
            pinCertSHABackupB = applicationContext.getString(R.string.pin_cert_back_up_b)
        )
        DaggerDataComponents.builder().appConfigs(appConfigs)
            .openWeatherLocalDatabase(frameworkComponents.openWeatherLocalDatabase()).build()
    }
    private val domainComponents: DomainComponents by lazy {
        DaggerDomainComponents.builder()
            .openWeatherRepository(dataComponents.openWeatherRepository()).build()
    }

    private val appComponents: AppComponents by lazy {
        DaggerAppComponents.builder().context(applicationContext)
            .openWeatherUseCases(domainComponents.openWeatherUseCases()).build()
    }

    override fun provideInjectionProvider(): InjectionProvider = appComponents

}