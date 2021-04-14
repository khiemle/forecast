package com.khiemle.utilities.datetime

import android.text.format.DateFormat
import java.util.*

internal const val DISPLAY_DATE_FORMAT = "EEE, dd MMM yyyy"

internal fun convertTimestampToDisplayDate(timestamp: Long) : String {
    val cal: Calendar = Calendar.getInstance()
    cal.timeInMillis = timestamp
    return DateFormat.format(DISPLAY_DATE_FORMAT, cal).toString()
}