package com.billyluisneedham.foodapp.domain.usecases.food

import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.domain.repositories.FoodRepository
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    suspend fun delete(food: Food) = foodRepository.deleteFood(food = food)
}
