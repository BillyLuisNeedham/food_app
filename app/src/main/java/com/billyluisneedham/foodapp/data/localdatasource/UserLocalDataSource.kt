package com.billyluisneedham.foodapp.data.localdatasource

import com.billyluisneedham.foodapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    fun getAllUsers(): Flow<List<com.billyluisneedham.foodapp.domain.models.User>>
    suspend fun add(user: com.billyluisneedham.foodapp.domain.models.User)
    suspend fun delete(user: com.billyluisneedham.foodapp.domain.models.User)
}
