package com.example.sqldelightprototype.domain

import java.lang.Exception

sealed class ResultOf<out T> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val message: String? = null, val exception: Exception? = null) :
        ResultOf<Nothing>()
    object Loading : ResultOf<Nothing>()
}
