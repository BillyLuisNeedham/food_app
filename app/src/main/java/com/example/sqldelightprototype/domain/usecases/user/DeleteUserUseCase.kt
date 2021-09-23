package com.example.sqldelightprototype.domain.usecases.user

import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.domain.repositories.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun delete(user: User) = userRepository.deleteUser(user = user)
}