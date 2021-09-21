package com.example.sqldelightprototype.presentation.models

import androidx.annotation.StringRes

data class ErrorState(
    val error: Boolean,
    @StringRes val messageResource: Int? = null
)
