package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.data.utils.extensionfunctions.makeAllStringsLowerCase
import com.example.sqldelightprototype.domain.models.User
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val foodDatabase: FoodDatabase
) : UserLocalDataSource {

    override suspend fun add(user: User) {
        val lowerCaseUser = user.makeAllStringsLowerCase()
        foodDatabase
            .queries.userQueries.insertOrReplace(
                id = lowerCaseUser.id,
                name = lowerCaseUser.name
            )
    }


}