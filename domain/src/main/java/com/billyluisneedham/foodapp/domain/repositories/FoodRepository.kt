package com.billyluisneedham.foodapp.domain.repositories

import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getAllFoodsSortedByName(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<Food>>>
    fun getAllFoodsSortedByExpiry(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<Food>>>
    fun getAllFoodsSortedByAmount(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<Food>>>
    suspend fun addFood(food: Food): com.billyluisneedham.foodapp.domain.ResultOf<Unit>
    suspend fun updateFood(food: Food): com.billyluisneedham.foodapp.domain.ResultOf<Unit>
    suspend fun deleteFood(food: Food): com.billyluisneedham.foodapp.domain.ResultOf<Unit>
    suspend fun deleteAllFoods(): com.billyluisneedham.foodapp.domain.ResultOf<Unit>
}
