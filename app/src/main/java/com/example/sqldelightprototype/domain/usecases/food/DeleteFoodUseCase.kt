package com.example.sqldelightprototype.domain.usecases.food

import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.repositories.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun delete(food: Food) = foodRepository.deleteFood(food = food)
}