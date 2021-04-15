package com.khiemle.nab.deps

import android.content.Context
import com.khiemle.nab.device.DeviceInfo
import com.scottyab.rootbeer.RootBeer
import dagger.Module
import dagger.Provides

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
}