package com.billyluisneedham.foodapp.domain.repositories

import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAllFoodsSortedByName(): Flow<ResultOf<List<Food>>>
    fun getAllFoodsSortedByExpiry(): Flow<ResultOf<List<Food>>>
    fun getAllFoodsSortedByAmount(): Flow<ResultOf<List<Food>>>
    suspend fun addFood(food: Food): ResultOf<Unit>
    suspend fun updateFood(food: Food): ResultOf<Unit>
    suspend fun deleteFood(food: Food): ResultOf<Unit>
    suspend fun deleteAllFoods(): ResultOf<Unit>
}
