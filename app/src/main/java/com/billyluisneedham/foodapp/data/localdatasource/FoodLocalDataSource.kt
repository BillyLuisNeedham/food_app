package com.billyluisneedham.foodapp.data.localdatasource

import com.billyluisneedham.foodapp.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodLocalDataSource {
    fun getAllSortedByName(): Flow<List<Food>>
    fun getAllSortedByExpiry(): Flow<List<Food>>
    fun getAllSortedByAmount(): Flow<List<Food>>
    suspend fun add(food: Food)
    suspend fun update(food: Food)
    suspend fun delete(food: Food)
    suspend fun deleteAll()
}
