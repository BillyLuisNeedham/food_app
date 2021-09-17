package com.example.sqldelightprototype.data.utils.time

interface TimeManager {
    fun getCurrentTimeStamp(): Long
    fun getCurrentTimeStampWithDaysAdded(days: Long): Long
    fun getAmountOfDaysFromNow(timeStampInMilliSeconds: Long): Long
}