package com.billyluisneedham.foodapp.domain.usecases.food

import com.billyluisneedham.foodapp.domain.repositories.FoodRepository
import javax.inject.Inject

class DeleteAllFoodsUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun deleteAll() = foodRepository.deleteAllFoods()
}
