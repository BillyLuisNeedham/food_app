package com.example.sqldelightprototype.data.utils.extensionfunctions

import com.example.sqldelightprototype.domain.models.User

fun User.makeAllStringsLowerCase() = this.copy(
    name = this.name.lowercase()
)

fun User.capitaliseStrings() = this.copy(
    name = this.name.captialise()
)