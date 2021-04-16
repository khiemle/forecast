package com.khiemle.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ForecastRDao {
    @Query("select cityId, timeStamp, averageTemperature, pressure, humidity, description from forecast inner join city_key_map on city_key_map.cityId = forecast.cityId where city_key_map.`query` like :searchKey and forecast.timeStamp >= :timestamp limit :count")
    fun loadAllForecasts(searchKey: String, timestamp: Long, count: Int): List<ForecastR>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecasts(forecasts: List<ForecastR>)

    @Query("delete from forecast")
    fun deleteAll()

    @Query("delete from forecast where timeStamp < :timestamp")
    fun deleteBeforeTimestamp(timestamp: Long)
}