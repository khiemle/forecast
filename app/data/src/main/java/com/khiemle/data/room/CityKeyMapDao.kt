package com.khiemle.data.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CityKeyMapDao {
    @Query("delete from city_key_map where timeStamp < :timestamp")
    fun deleteBeforeTimestamp(timestamp: Long)
}