package com.khiemle.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khiemle.data.room.models.CityKeyMap
import com.khiemle.data.room.models.ForecastR


@Database(entities = [ForecastR::class, CityKeyMap::class], version = 1, exportSchema = false)
abstract class ForecastDatabase: RoomDatabase() {
    abstract fun forecastRDao(): ForecastRDao
    abstract fun cityKeyMapDao(): CityKeyMapDao

    companion object {
        private const val DATABASE_NAME = "Forecast"
        fun buildDatabase(context: Context): ForecastDatabase {
            return Room.databaseBuilder(context, ForecastDatabase::class.java, DATABASE_NAME).build()
        }
    }
}