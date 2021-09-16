package com.example.sqldelightprototype.data.repositories

import android.util.Log
import com.example.sqldelightprototype.data.localdatasource.FoodLocalDataSource
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.repositories.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodLocalDataSource: FoodLocalDataSource
) : FoodRepository {

    companion object {
        private const val TAG = "FoodRepositoryImpl"
    }

    override fun getAllFoods(): Flow<ResultOf<List<Food>>> {
        return flow {
            emit(ResultOf.Loading)

            try {
                foodLocalDataSource.getAll().collect {
                    emit(ResultOf.Success(data = it))
                }
            } catch (e: Exception) {
                Log.e(TAG, "error in getAllFoods: $e")
                emit(ResultOf.Error(exception = e))
            }

        }
    }

    override suspend fun addFood(food: Food) {
        TODO("Not yet implemented")
    }
}