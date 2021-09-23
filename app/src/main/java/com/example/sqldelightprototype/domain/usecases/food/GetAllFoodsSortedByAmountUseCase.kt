package com.example.sqldelightprototype.domain.usecases.food

import com.example.sqldelightprototype.domain.repositories.FoodRepository
import javax.inject.Inject

class GetAllFoodsSortedByAmountUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    fun get() = foodRepository.getAllFoodsSortedByAmount()
}