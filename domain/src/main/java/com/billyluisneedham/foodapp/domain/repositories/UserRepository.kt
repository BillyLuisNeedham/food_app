package com.billyluisneedham.foodapp.domain.repositories

import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<User>>>
    suspend fun addUser(user: User): com.billyluisneedham.foodapp.domain.ResultOf<Unit>
    suspend fun deleteUser(user: User): com.billyluisneedham.foodapp.domain.ResultOf<Unit>
}
