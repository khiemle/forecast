package com.khiemle.data.deps

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, OpenWeatherModule::class])
interface DataComponents