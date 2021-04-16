package com.khiemle.data.room.models

import androidx.room.Entity

@Entity(tableName = "city_key_map", primaryKeys = ["cityId"])
data class CityKeyMap(
    val cityId: Long = 0L,
    val query: String? = null,
    val timestamp: Long = 0L
)