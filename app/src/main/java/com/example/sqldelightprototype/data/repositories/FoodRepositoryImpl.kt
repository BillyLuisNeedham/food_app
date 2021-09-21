package com.example.sqldelightprototype.data.repositories

import android.nfc.Tag
import android.util.Log
import com.example.sqldelightprototype.data.localdatasource.FoodLocalDataSource
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.repositories.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodLocalDataSource: FoodLocalDataSource
) : FoodRepository {

    companion object {
        private const val TAG = "FoodRepositoryImpl"
    }

    override fun getAllFoods(): Flow<ResultOf<List<Food>>> =
        try {
            foodLocalDataSource.getAll().mapNotNull {
                ResultOf.Success(it)
            }
        } catch (e: Exception) {
            Log.e(TAG, "exception in getAllFoods: $e")
            flow { emit(ResultOf.Error(exception = e)) }
        }


    override suspend fun addFood(food: Food): ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                foodLocalDataSource.add(food = food)
                ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within addFood: $e")
                ResultOf.Error(exception = e)
            }
        }

    override suspend fun updateFood(food: Food): ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                val foodToUpdate = when {
                    food.quantity < 0 -> food.copy(quantity = 0)
                    else -> food
                }
                foodLocalDataSource.update(food = foodToUpdate)
                ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within updateFood: $e")
                ResultOf.Error(exception = e)
            }
        }

    override suspend fun deleteFood(food: Food): ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                foodLocalDataSource.delete(food = food)
                ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within deleteFood: $e")
                ResultOf.Error(exception = e)
            }
        }

    override suspend fun deleteAllFoods(): ResultOf<Unit> =
        withContext(Dispatchers.IO) {
            try {
                foodLocalDataSource.deleteAll()
                ResultOf.Success(data = Unit)
            } catch (e: Exception) {
                Log.e(TAG, "exception within deleteAllFoods: $e")
                ResultOf.Error(exception = e)
            }
        }
}