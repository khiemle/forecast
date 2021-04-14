package com.khiemle.nab

import android.app.Application
import com.khiemle.nab.deps.DependenciesProvider
import com.khiemle.nab.deps.InjectionProvider

class ForecastApplication: Application(), DependenciesProvider {
    override fun provideInjectionProvider(): InjectionProvider {
        TODO("Not yet implemented")
    }
}