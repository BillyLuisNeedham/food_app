package com.billyluisneedham.foodapp.data.localdatasource

import com.billyluisneedham.foodapp.domain.models.Food
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import java.lang.IllegalStateException
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class FoodLocalDataSourceImpl @Inject constructor(
    private val foodDatabase: FoodDatabase
) : FoodLocalDataSource {

    companion object {
        private const val TAG = "FoodLocalDataSourceImpl"
    }

    override fun getAllSortedByName(): Flow<List<com.billyluisneedham.foodapp.domain.models.Food>> =
        foodDatabase
            .queries.foodQueries.selectAllSortByName(
                mapper = { id, name, quantity, expirationDate ->
                    com.billyluisneedham.foodapp.domain.models.Food(
                        id = id,
                        name = name,
                        quantity = quantity.toInt(),
                        expirationDate = expirationDate
                    )
                }
            ).asFlow().mapToList()

    override fun getAllSortedByExpiry(): Flow<List<com.billyluisneedham.foodapp.domain.models.Food>> =
        foodDatabase
            .queries.foodQueries.selectAllSortByExpiry(
                mapper = { id, name, quantity, expirationDate ->
                    com.billyluisneedham.foodapp.domain.models.Food(
                        id = id,
                        name = name,
                        quantity = quantity.toInt(),
                        expirationDate = expirationDate
                    )
                }
            ).asFlow().mapToList()

    override fun getAllSortedByAmount(): Flow<List<com.billyluisneedham.foodapp.domain.models.Food>> =
        foodDatabase
            .queries.foodQueries.selectAllSortByAmount(
                mapper = { id, name, quantity, expirationDate ->
                    com.billyluisneedham.foodapp.domain.models.Food(
                        id = id,
                        name = name,
                        quantity = quantity.toInt(),
                        expirationDate = expirationDate
                    )
                }
            ).asFlow().mapToList()

    override suspend fun add(food: com.billyluisneedham.foodapp.domain.models.Food) =
        foodDatabase
            .queries.foodQueries.insertOrReplace(
                id = food.id,
                name = food.name,
                quantity = food.quantity.toLong(),
                expiry_date = food.expirationDate
            )

    override suspend fun update(food: com.billyluisneedham.foodapp.domain.models.Food) = add(food = food)

    override suspend fun delete(food: com.billyluisneedham.foodapp.domain.models.Food) {
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
