package com.billyluisneedham.foodapp.domain.usecases.food

import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.domain.repositories.FoodRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllFoodsSortedByExpiryUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    fun get(): Flow<ResultOf<List<Food>>> = foodRepository.getAllFoodsSortedByExpiry()
}
