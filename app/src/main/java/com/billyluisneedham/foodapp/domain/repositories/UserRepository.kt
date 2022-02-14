package com.billyluisneedham.foodapp.domain.repositories

import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<ResultOf<List<User>>>
    suspend fun addUser(user: User): ResultOf<Unit>
    suspend fun deleteUser(user: User): ResultOf<Unit>
}
