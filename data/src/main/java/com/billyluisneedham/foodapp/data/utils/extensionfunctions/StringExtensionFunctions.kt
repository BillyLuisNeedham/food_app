package com.billyluisneedham.foodapp.data.utils.extensionfunctions

fun String.captialise() =
    this.replaceFirstChar {
        it.uppercase()
    }
