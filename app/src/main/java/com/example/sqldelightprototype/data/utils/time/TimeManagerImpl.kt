package com.example.sqldelightprototype.data.utils.time

import java.util.*
import javax.inject.Inject

class TimeManagerImpl @Inject constructor() : TimeManager {
    companion object {
        private const val HOURS_IN_DAY = 24
        private const val MINUTES_IN_HOUR = 60
        private const val SECONDS_IN_MINUTE = 60
        private const val MILLISECONDS_IN_SECOND = 1000
    }

    override fun getCurrentTimeStamp(): Long = System.currentTimeMillis()

    override fun getCurrentTimeStampWithDaysAdded(days: Long): Long {
        val daysInMilliSeconds =
            days * MILLISECONDS_IN_SECOND * SECONDS_IN_MINUTE * MINUTES_IN_HOUR * HOURS_IN_DAY

        return getCurrentTimeStamp() + daysInMilliSeconds
    }

    override fun getAmountOfDaysFromNow(timeStampInMilliSeconds: Long): Long {
        val now = getCurrentTimeStamp()
        val difference = timeStampInMilliSeconds - now
        return difference / MILLISECONDS_IN_SECOND / SECONDS_IN_MINUTE / MINUTES_IN_HOUR / HOURS_IN_DAY
    }

    override fun getTimeStampForCurrentLocaleFrom(
        year: Int,
        month: Int,
        day: Int
    ): Long {
        val c = Calendar.getInstance()
        c.set(year, month, day)
        return c.timeInMillis
    }
}
