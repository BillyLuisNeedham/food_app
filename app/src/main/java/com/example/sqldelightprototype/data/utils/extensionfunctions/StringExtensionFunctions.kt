package com.example.sqldelightprototype.data.utils.extensionfunctions

fun String.captialise() =
    this.replaceFirstChar {
        it.uppercase()
    }