package com.billyluisneedham.foodapp.domain.usecases.user

import com.billyluisneedham.foodapp.domain.models.User
import com.billyluisneedham.foodapp.domain.repositories.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun delete(user: User) = userRepository.deleteUser(user = user)
}
