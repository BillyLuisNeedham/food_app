package com.example.sqldelightprototype.data.utils.time

import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TimeManagerImpl @Inject constructor() : TimeManager {

    override fun getCurrentTimeStamp(): Long = System.currentTimeMillis()


    override fun getCurrentTimeStampWithDaysAdded(days: Long): Long {
        val daysInMilliSeconds = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS)
        return getCurrentTimeStamp() + daysInMilliSeconds
    }

    override fun getAmountOfDaysFromNow(timeStampInMilliSeconds: Long): Long {
        val now = getCurrentTimeStamp()
        val difference = timeStampInMilliSeconds - now
        return TimeUnit.MILLISECONDS.convert(difference, TimeUnit.DAYS)
    }
}