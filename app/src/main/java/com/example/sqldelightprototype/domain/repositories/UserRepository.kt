package com.example.sqldelightprototype.domain.repositories

import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsers(): Flow<ResultOf<List<User>>>
    suspend fun addUser(user: User): ResultOf<Unit>

}
