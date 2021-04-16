package com.khiemle.data.room

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.khiemle.data.room.models.CityKeyMap
import com.khiemle.data.room.models.ForecastR
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class ForecastDatabaseTest {

    private lateinit var forecastRDao: ForecastRDao
    private lateinit var cityKeyMapDao: CityKeyMapDao
    private lateinit var db: ForecastDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, ForecastDatabase::class.java).build()
        forecastRDao = db.forecastRDao()
        cityKeyMapDao = db.cityKeyMapDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeForecastSearchKeyAndQueryInList() {
        val forecastR = ForecastR(
            cityId = 1,
            timeStamp = 1000L,
            averageTemperature = 30.toDouble(),
            pressure = 1000,
            humidity = 70,
            description = "rain"
        )
        val oldForecastR = ForecastR(
            cityId = 2,
            timeStamp = 200L,
            averageTemperature = 16.toDouble(),
            pressure = 2000,
            humidity = 80,
            description = "sunny"
        )
        val cityKeyMap = CityKeyMap(
            cityId = 1,
            timestamp = 1000L,
            query = "saigon"
        )
        forecastRDao.insertForecasts(listOf(oldForecastR,forecastR))
        cityKeyMapDao.insertCityKeyMap(cityKeyMap)
        val dailyForecast = forecastRDao.loadAllForecasts(searchKey = "saigon", timestamp = 900L, count = 1)
        assertTrue(dailyForecast.size == 1)
        with(dailyForecast.first()) {
            assertEquals(forecastR.cityId, cityId)
            assertEquals(forecastR.timeStamp, timeStamp)
            assertEquals(forecastR.humidity, humidity)
            assertEquals(forecastR.pressure, pressure)
            assertEquals(forecastR.description, description)
        }

    }
}