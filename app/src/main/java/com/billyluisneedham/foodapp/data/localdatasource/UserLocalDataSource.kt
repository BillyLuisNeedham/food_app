package com.billyluisneedham.foodapp.data.localdatasource

import com.billyluisneedham.foodapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun getAllUsers(): Flow<List<User>>
    suspend fun add(user: User)
    suspend fun delete(user: User)
}
