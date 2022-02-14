package com.billyluisneedham.foodapp.presentation.util.expirationmessage

interface ExpirationMessage {
    fun getMessageForTimeStamp(timeStampInMilliSeconds: Long): String
}
