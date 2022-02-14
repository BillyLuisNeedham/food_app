package com.billyluisneedham.foodapp.data.localdatasource

import com.billyluisneedham.foodapp.data.utils.extensionfunctions.makeAllStringsLowerCase
import com.billyluisneedham.foodapp.domain.models.User
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

    override fun getAllUsers(): Flow<List<com.billyluisneedham.foodapp.domain.models.User>> =
        foodDatabase
            .queries.userQueries.selectAll { id, name ->
                com.billyluisneedham.foodapp.domain.models.User(
                    id = id,
                    name = name
                )
            }.asFlow().mapToList()

    override suspend fun add(user: com.billyluisneedham.foodapp.domain.models.User) {
        val lowerCaseUser = user.makeAllStringsLowerCase()
        foodDatabase
            .queries.userQueries.insertOrReplace(
                id = lowerCaseUser.id,
                name = lowerCaseUser.name
            )
    }

    override suspend fun delete(user: com.billyluisneedham.foodapp.domain.models.User) {
        foodDatabase
            .queries.userQueries.delete(
                id = user.id
                    ?: throw IllegalStateException(
                        "$TAG: user.id is null and must not be"
                    )
            )
    }
}
