package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.domain.models.Food
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodLocalDataSourceImpl @Inject constructor(
    private val foodDatabase: FoodDatabase
) : FoodLocalDataSource {

    override fun getAll(): Flow<List<Food>> =
        foodDatabase.queries.foodQueries.selectAll(mapper = { id, name, quantity, expirationDate ->
            Food(
                id = id,
                name = name,
                quantity = quantity.toInt(),
                expirationDate = expirationDate
            )
        }).asFlow().mapToList()


    override suspend fun add(food: Food) =
        foodDatabase
            .queries.foodQueries.insertOrReplace(
                id = food.id,
                name = food.name,
                quantity = food.quantity.toLong(),
                expiry_date = food.expirationDate
            )
}