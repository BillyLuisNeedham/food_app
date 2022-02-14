package com.billyluisneedham.foodapp.data.utils.extensionfunctions

import com.billyluisneedham.foodapp.domain.models.User

fun com.billyluisneedham.foodapp.domain.models.User.makeAllStringsLowerCase() = this.copy(
    name = this.name.lowercase()
)

fun com.billyluisneedham.foodapp.domain.models.User.capitaliseStrings() = this.copy(
    name = capitaliseEachName(this.name)
)

private fun capitaliseEachName(name: String): String {
    val names = name.split(" ")
    val capitalisedNames = names.map {
        it.captialise()
    }
    return capitalisedNames.joinToString(" ")
}
