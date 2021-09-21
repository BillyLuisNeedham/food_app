package com.example.sqldelightprototype.presentation.util

interface ExpirationMessage {
    fun getMessageForTimeStamp(timeStampInMilliSeconds: Long): String
}