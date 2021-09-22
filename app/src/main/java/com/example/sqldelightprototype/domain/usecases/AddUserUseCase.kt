package com.example.sqldelightprototype.domain.usecases

import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.domain.repositories.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun add(user: User) = userRepository.addUser(user = user)
}