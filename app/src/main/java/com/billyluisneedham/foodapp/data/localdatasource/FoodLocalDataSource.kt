package com.billyluisneedham.foodapp.data.localdatasource

import com.billyluisneedham.foodapp.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodLocalDataSource {
    fun getAllSortedByName(): Flow<List<com.billyluisneedham.foodapp.domain.models.Food>>
    fun getAllSortedByExpiry(): Flow<List<com.billyluisneedham.foodapp.domain.models.Food>>
    fun getAllSortedByAmount(): Flow<List<com.billyluisneedham.foodapp.domain.models.Food>>
    suspend fun add(food: com.billyluisneedham.foodapp.domain.models.Food)
    suspend fun update(food: com.billyluisneedham.foodapp.domain.models.Food)
    suspend fun delete(food: com.billyluisneedham.foodapp.domain.models.Food)
    suspend fun deleteAll()
}
