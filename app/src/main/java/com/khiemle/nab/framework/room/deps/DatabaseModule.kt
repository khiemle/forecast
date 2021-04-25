package com.khiemle.nab.framework.room

import android.content.Context
import com.khiemle.data.local.IOpenWeatherLocal
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    fun provideForecastDatabase(applicationContext: Context): ForecastDatabase {
        return ForecastDatabase.buildDatabase(applicationContext)
    }
}

@Module
abstract class LocalDataSourceModule {
    @Binds
    abstract fun provideForecastDatabase(o: OpenWeatherLocal): IOpenWeatherLocal
}