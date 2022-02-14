package com.billyluisneedham.foodapp.data.repositories

import android.util.Log
import com.billyluisneedham.foodapp.data.localdatasource.FoodLocalDataSource
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.domain.repositories.FoodRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext

class FoodRepositoryImpl @Inject constructor(
    private val foodLocalDataSource: FoodLocalDataSource
) : com.billyluisneedham.foodapp.domain.repositories.FoodRepository {

    companion object {
        private const val TAG = "FoodRepositoryImpl"
    }

    override fun getAllFoodsSortedByName(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<com.billyluisneedham.foodapp.domain.models.Food>>> =
        getAll {
            foodLocalDataSource.getAllSortedByName()
        }

    override fun getAllFoodsSortedByExpiry(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<com.billyluisneedham.foodapp.domain.models.Food>>> =
        getAll {
            foodLocalDataSource.getAllSortedByExpiry()
        }

    override fun getAllFoodsSortedByAmount(): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<com.billyluisneedham.foodapp.domain.models.Food>>> =
        getAll {
            foodLocalDataSource.getAllSortedByAmount()
        }

    private fun getAll(getCallback: () -> Flow<List<com.billyluisneedham.foodapp.domain.models.Food>>): Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<com.billyluisneedham.foodapp.domain.models.Food>>> =
        try {
            getCallback().mapNotNull {
                com.billyluisneedham.foodapp.domain.ResultOf.Success(it)
            }
        } catch (e: Exception) {
            Log.e(TAG, "exception within getAll: $e")
            flow { emit(com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)) }
        }

    override suspend fun addFood(food: com.billyluisneedham.foodapp.domain.models.Food): com.billyluisneedham.foodapp.domain.ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                foodLocalDataSource.add(food = food)
                com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within addFood: $e")
                com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)
            }
        }

    override suspend fun updateFood(food: com.billyluisneedham.foodapp.domain.models.Food): com.billyluisneedham.foodapp.domain.ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val foodToUpdate = when {
                    food.quantity < 0 -> food.copy(quantity = 0)
                    else -> food
                }
                foodLocalDataSource.update(food = foodToUpdate)
                com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within updateFood: $e")
                com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)
            }
        }

    override suspend fun deleteFood(food: com.billyluisneedham.foodapp.domain.models.Food): com.billyluisneedham.foodapp.domain.ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                foodLocalDataSource.delete(food = food)
                com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within deleteFood: $e")
                com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)
            }
        }

    override suspend fun deleteAllFoods(): com.billyluisneedham.foodapp.domain.ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                foodLocalDataSource.deleteAll()
                com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within deleteAllFoods: $e")
                com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)
            }
        }
}
