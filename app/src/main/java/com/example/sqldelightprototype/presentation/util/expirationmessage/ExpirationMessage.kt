package com.example.sqldelightprototype.presentation.util.expirationmessage

interface ExpirationMessage {
    fun getMessageForTimeStamp(timeStampInMilliSeconds: Long): String
}