package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun getAllUsers(): Flow<List<User>>
    suspend fun add(user: User)
    suspend fun delete(user: User)
}
