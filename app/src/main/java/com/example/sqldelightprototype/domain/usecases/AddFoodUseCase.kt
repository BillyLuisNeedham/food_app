package com.example.sqldelightprototype.domain.usecases

import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.repositories.FoodRepository
import javax.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    fun add(food: Food) = foodRepository.addFood(food = food)
}