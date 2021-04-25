package com.khiemle.nab.framework.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.khiemle.nab.framework.room.models.CityKeyMap

@Dao
interface CityKeyMapDao {
    @Query("delete from city_key_map where timeStamp < :timestamp")
    fun deleteBeforeTimestamp(timestamp: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCityKeyMap(cityKeyMap: CityKeyMap)
}