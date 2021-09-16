package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.domain.models.Food
import kotlinx.coroutines.flow.Flow

interface FoodLocalDataSource {
    fun getAll(): Flow<List<Food>>
    fun add(food: Food)
}