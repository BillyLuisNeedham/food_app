package com.example.sqldelightprototype.domain.usecases

import com.example.sqldelightprototype.domain.repositories.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun get() = userRepository.getAllUsers()
}