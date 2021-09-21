package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodLocalDataSource {
    fun  getAll(): Flow<List<Food>>
    suspend fun add(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    suspend fun deleteAll()
}