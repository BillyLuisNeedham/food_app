package com.example.sqldelightprototype.data.utils.time

import javax.inject.Inject

class TimeManagerImpl @Inject constructor() : TimeManager {

    override fun getCurrentTimeStamp(): Long = System.currentTimeMillis()

    override fun getCurrentTimeStampWithDaysAdded(days: Long): Long {
        val daysInMilliSeconds = days * 24 * 60 * 60 * 1000

        return getCurrentTimeStamp() + daysInMilliSeconds
    }

    override fun getAmountOfDaysFromNow(timeStampInMilliSeconds: Long): Long {
        val now = getCurrentTimeStamp()
        val difference = timeStampInMilliSeconds - now
        return difference / 1000 / 60 / 60 / 24
    }
}