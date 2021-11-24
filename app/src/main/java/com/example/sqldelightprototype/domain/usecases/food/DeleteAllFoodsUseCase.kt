package com.example.sqldelightprototype.domain.usecases.food

import com.example.sqldelightprototype.domain.repositories.FoodRepository
import javax.inject.Inject

class DeleteAllFoodsUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun deleteAll() = foodRepository.deleteAllFoods()
}
