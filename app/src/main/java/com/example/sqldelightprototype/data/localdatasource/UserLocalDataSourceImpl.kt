package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.data.utils.extensionfunctions.makeAllStringsLowerCase
import com.example.sqldelightprototype.domain.models.User
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val foodDatabase: FoodDatabase
) : UserLocalDataSource {

    override fun getAllUsers(): Flow<List<User>> =
        foodDatabase
            .queries.userQueries.selectAll { id, name ->
                User(
                    id = id,
                    name = name
                )
            }.asFlow().mapToList()

    override suspend fun add(user: User) {
        val lowerCaseUser = user.makeAllStringsLowerCase()
        foodDatabase
            .queries.userQueries.insertOrReplace(
                id = lowerCaseUser.id,
                name = lowerCaseUser.name
            )
    }


}