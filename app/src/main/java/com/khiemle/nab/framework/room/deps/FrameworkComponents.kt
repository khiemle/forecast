package com.khiemle.nab.framework.room.deps

import android.content.Context
import com.khiemle.data.local.IOpenWeatherLocal
import com.khiemle.nab.framework.room.DatabaseModule
import com.khiemle.nab.framework.room.LocalDataSourceModule
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