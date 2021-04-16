package com.khiemle.utilities.datetime

import android.text.format.DateFormat
import java.util.*

internal const val DISPLAY_DATE_FORMAT = "EEE, dd MMM yyyy"

fun convertTimestampToDisplayDate(timestamp: Long) : String {
    val cal: Calendar = Calendar.getInstance()
    cal.timeInMillis = timestamp
    return DateFormat.format(DISPLAY_DATE_FORMAT, cal).toString()
}

fun getCurrentTimeInMillis(): Long {
    val now = Calendar.getInstance()
    val hour = now[Calendar.HOUR_OF_DAY]
    val minute = now[Calendar.MINUTE]
    val second = now[Calendar.SECOND]
    val millis = now[Calendar.MILLISECOND]
    return System.currentTimeMillis() - ((hour * 24 + minute) * 60 + second) * 1000 + millis
}