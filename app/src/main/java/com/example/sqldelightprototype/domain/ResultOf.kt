package com.example.sqldelightprototype.domain

import java.lang.Exception

sealed class ResultOf<out T> {
    data class Success<out T>(val data: T) : ResultOf<T>()
    data class Error(val message: String?, val exception: Exception?) : ResultOf<Nothing>()
    object Loading : ResultOf<Nothing>()
}
