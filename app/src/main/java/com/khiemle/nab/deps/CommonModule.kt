package com.khiemle.nab.deps

import android.content.Context
import com.khiemle.nab.device.DeviceInfo
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

internal const val IO_DISPATCHER = "io_dispatcher"

@Module
internal class CommonModule {
    @Provides
    fun provideRootBeer(context: Context): RootBeer {
        return RootBeer(context)
    }

    @Provides
    fun provideDeviceInfo(rootBeer: RootBeer): DeviceInfo {
        return DeviceInfo(isRooted = rootBeer.isRooted)
    }

    @Provides
    @Named(IO_DISPATCHER)
    fun provideIODispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}