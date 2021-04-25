package com.khiemle.nab.framework.room.deps

import android.content.Context
import androidx.room.Database
import com.khiemle.data.local.IOpenWeatherLocal
import com.khiemle.domain.openweather.usecases.IOpenWeatherUseCases
import com.khiemle.nab.framework.room.DatabaseModule
import com.khiemle.nab.framework.room.LocalDataSourceModule
import com.khiemle.nab.presentation.deps.ViewModelsModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [DatabaseModule::class, LocalDataSourceModule::class])
interface FrameworkComponents: FrameworkDependenciesProvider {

    override fun openWeatherLocalDatabase(): IOpenWeatherLocal

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): FrameworkComponents
    }
}