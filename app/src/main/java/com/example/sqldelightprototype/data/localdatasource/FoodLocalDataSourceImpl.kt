package com.example.sqldelightprototype.data.localdatasource

import com.example.sqldelightprototype.domain.models.Food
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import java.lang.IllegalStateException
import javax.inject.Inject

class FoodLocalDataSourceImpl @Inject constructor(
    private val foodDatabase: FoodDatabase
) : FoodLocalDataSource {

    companion object {
        private const val TAG = "FoodLocalDataSourceImpl"
    }

    override fun getAllSortedByName(): Flow<List<Food>> =
        foodDatabase
            .queries.foodQueries.selectAllSortByName(
                mapper = { id, name, quantity, expirationDate ->
                    Food(
                        id = id,
                        name = name,
                        quantity = quantity.toInt(),
                        expirationDate = expirationDate
                    )
                }).asFlow().mapToList()

    override fun getAllSortedByExpiry(): Flow<List<Food>> =
        foodDatabase
            .queries.foodQueries.selectAllSortByExpiry(
                mapper = { id, name, quantity, expirationDate ->
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

    override suspend fun update(food: Food) = add(food = food)

    override suspend fun delete(food: Food) {
        food.id?.let {
            foodDatabase
                .queries.foodQueries.deleteById(id = food.id)
        } ?: throw IllegalStateException("$TAG: food.id is null and must not be")
    }

    override suspend fun deleteAll() {
        foodDatabase
            .queries.foodQueries.deleteAll()
    }
}