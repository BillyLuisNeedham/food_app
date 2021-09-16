package com.example.sqldelightprototype.domain.usecases

import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.repositories.FoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFoodsUseCase @Inject constructor(
    private val foodRepository: FoodRepository
){
    fun get(): ResultOf<Flow<List<Food>>> = foodRepository.getAllFoods()
}