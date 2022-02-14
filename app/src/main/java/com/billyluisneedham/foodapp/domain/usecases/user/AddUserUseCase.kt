package com.billyluisneedham.foodapp.domain.usecases.user

import com.billyluisneedham.foodapp.domain.models.User
import com.billyluisneedham.foodapp.domain.repositories.UserRepository
import javax.inject.Inject

class AddUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun add(user: User) = userRepository.addUser(user = user)
}
