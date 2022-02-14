package com.billyluisneedham.foodapp.presentation.util.expirationmessage

import android.content.Context
import com.billyluisneedham.foodapp.R
import com.billyluisneedham.foodapp.data.utils.time.TimeManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ExpirationMessageImpl @Inject constructor(
    private val timeManager: com.billyluisneedham.foodapp.data.utils.time.TimeManager,
    @ApplicationContext val context: Context
) : ExpirationMessage {

    override fun getMessageForTimeStamp(
        timeStampInMilliSeconds: Long
    ): String {
        val daysFromNow = timeManager.getAmountOfDaysFromNow(
            timeStampInMilliSeconds = timeStampInMilliSeconds
        )
        return getMessageForDays(
            days = daysFromNow + 1,
        )
    }

    private fun getMessageForDays(
        days: Long,
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
