package com.khiemle.data.deps

import android.content.Context
import com.khiemle.data.room.ForecastDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideForecastDatabase(applicationContext: Context): ForecastDatabase {
        return ForecastDatabase.buildDatabase(applicationContext)
    }
}