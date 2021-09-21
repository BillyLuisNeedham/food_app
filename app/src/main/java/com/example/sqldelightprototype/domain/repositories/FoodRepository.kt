package com.example.sqldelightprototype.domain.repositories

import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAllFoodsSortedByName(): Flow<ResultOf<List<Food>>>
    fun getAllFoodsSortedByExpiry(): Flow<ResultOf<List<Food>>>
    suspend fun addFood(food: Food): ResultOf<Unit>
    suspend fun updateFood(food: Food): ResultOf<Unit>
    suspend fun deleteFood(food: Food): ResultOf<Unit>
    suspend fun deleteAllFoods(): ResultOf<Unit>
}
