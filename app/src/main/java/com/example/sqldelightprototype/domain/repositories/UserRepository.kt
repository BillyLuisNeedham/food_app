package com.example.sqldelightprototype.domain.repositories

import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.User

interface UserRepository {
    suspend fun addUser(user: User): ResultOf<Unit>

}
