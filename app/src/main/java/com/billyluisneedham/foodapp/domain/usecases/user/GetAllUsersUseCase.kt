package com.billyluisneedham.foodapp.domain.usecases.user

import com.billyluisneedham.foodapp.domain.repositories.UserRepository
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    fun get() = userRepository.getAllUsers()
}
