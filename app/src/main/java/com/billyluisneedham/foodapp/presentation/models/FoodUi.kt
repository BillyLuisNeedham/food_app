package com.billyluisneedham.foodapp.presentation.models

data class FoodUi(
    val id: Long,
    val name: String,
    val quantity: Int,
    val expirationDate: Long,
    val expirationMessage: String
)
