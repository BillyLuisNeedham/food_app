package com.billyluisneedham.foodapp.domain.usecases.food

import com.billyluisneedham.foodapp.domain.repositories.FoodRepository
import javax.inject.Inject

class GetAllFoodsSortedByAmountUseCase @Inject constructor(
    private val foodRepository: FoodRepository
) {
    fun get() = foodRepository.getAllFoodsSortedByAmount()
}
