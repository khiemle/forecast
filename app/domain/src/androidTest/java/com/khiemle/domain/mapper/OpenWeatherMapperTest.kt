package com.khiemle.domain.mapper

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.khiemle.domain.openweather.mapper.convertTimestampToDisplayDate
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
internal class OpenWeatherMapperTest {
    @Test
    internal fun testFuncConvertTimestampToDisplayDate(){
        val displayDate = convertTimestampToDisplayDate(TIMESTAMP_TEST)
        assertEquals(EXPECTED_DISPLAY_DATE, displayDate)
    }

    companion object {
        const val TIMESTAMP_TEST = 1618286400000L
        const val EXPECTED_DISPLAY_DATE = "Tue, 13 Apr 2021"
    }
}