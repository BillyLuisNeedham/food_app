package com.example.sqldelightprototype.presentation.util

import android.content.Context
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.data.utils.time.TimeManager
import javax.inject.Inject

class ExpirationMessageImpl @Inject constructor(
    private val timeManager: TimeManager
) : ExpirationMessage {

    override fun getMessageForTimeStamp(
        timeStampInMilliSeconds: Long,
        context: Context
    ): String {
        val daysFromNow = timeManager.getAmountOfDaysFromNow(
            timeStampInMilliSeconds = timeStampInMilliSeconds
        )
        return getMessageForDays(
            days = daysFromNow,
            context = context
        )
    }

    private fun getMessageForDays(
        days: Long,
        context: Context
    ): String =
        when {
            days < 0L -> context.getString(R.string.has_expired)
            days == 0L -> context.getString(R.string.today)
            days == 1L -> context.getString(R.string.one_day)
            else -> {
                val daysString = context.getString(R.string.days)
                "$days $daysString"
            }
        }
}