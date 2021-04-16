package com.khiemle.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ForecastR::class, CityKeyMap::class], version = 1, exportSchema = true)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun forecastRDao(): ForecastRDao
    abstract fun cityKeyMapDao(): CityKeyMapDao
}