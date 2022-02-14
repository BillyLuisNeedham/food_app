package com.billyluisneedham.foodapp.domain.models

data class Food(
    val id: Long? = null,
    val name: String,
    val quantity: Int,
    val expirationDate: Long,
)
