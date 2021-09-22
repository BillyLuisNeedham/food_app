package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.domain.models.User

interface UserLocalDataSource {
    suspend fun add(user: User)
}
