package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.data.utils.extensionfunctions.makeAllStringsLowerCase
import com.example.sqldelightprototype.domain.models.User
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl @Inject constructor(
    private val foodDatabase: FoodDatabase
) : UserLocalDataSource {

    companion object {
        private const val TAG = "UserLocalDataSourceImpl"
    }

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

    override suspend fun delete(user: User) {
        foodDatabase
            .queries.userQueries.delete(
                id = user.id
                    ?: throw IllegalStateException(
                        "$TAG: user.id is null and must not be"
                    )
            )
    }
}
