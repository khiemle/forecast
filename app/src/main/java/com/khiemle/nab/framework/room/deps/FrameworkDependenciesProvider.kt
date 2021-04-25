package com.khiemle.nab.framework.room.deps

import com.khiemle.data.local.IOpenWeatherLocal

interface FrameworkDependenciesProvider {
    fun openWeatherLocalDatabase(): IOpenWeatherLocal
}