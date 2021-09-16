package com.example.sqldelightprototype.domain.repositories

import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAllFoods(): Flow<ResultOf<List<Food>>>
    suspend fun addFood(food: Food): ResultOf<Unit>
}
