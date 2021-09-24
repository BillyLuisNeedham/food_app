package com.example.sqldelightprototype.data.utils.extensionfunctions

import com.example.sqldelightprototype.domain.models.User

fun User.makeAllStringsLowerCase() = this.copy(
    name = this.name.lowercase()
)

fun User.capitaliseStrings() = this.copy(
    name = capitaliseEachName(this.name)
)

private fun capitaliseEachName(name: String): String {
    val names = name.split(" ")
    val capitalisedNames = names.map {
        it.captialise()
    }
    return ca //TODO write
}
