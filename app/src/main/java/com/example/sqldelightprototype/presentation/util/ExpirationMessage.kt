package com.example.sqldelightprototype.presentation.util

import android.content.Context

interface ExpirationMessage {
    fun getMessageForTimeStamp(timeStampInMilliSeconds: Long, context: Context): String
}