package com.example.sqldelightprototype.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.usecases.DeleteFoodUseCase
import com.example.sqldelightprototype.domain.usecases.GetAllFoodsUseCase
import com.example.sqldelightprototype.domain.usecases.UpdateFoodUseCase
import com.example.sqldelightprototype.presentation.mappers.FoodUiMapper
import com.example.sqldelightprototype.presentation.models.FoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FoodListScreenViewModel @Inject constructor(
    private val getAllFoodsUseCase: GetAllFoodsUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase,
    private val updateFoodUseCase: UpdateFoodUseCase,
    private val foodUiMapper: FoodUiMapper
) : ViewModel() {

    companion object {
        private const val TAG = "FoodListScreenViewModel"
    }

    fun getAllFoods(context: Context) = getAllFoodsUseCase.get().map {
        when (it) {
            is ResultOf.Error -> handleMappingErrorToUiModel(error = it)
            ResultOf.Loading -> ResultOf.Loading
            is ResultOf.Success -> handleMappingSuccessToUiModel(
                models = it,
                context = context
            )
        }
    }

    fun deleteFood(foodUi: FoodUi) {
        viewModelScope.launch {
            try {
                val food = foodUi.mapFoodUiToFood()
                deleteFoodUseCase.delete(food = food)
            } catch (e: Exception) {
                Log.e(TAG, "exception within deleteFood: $e")
                //TODO display error to user
            }
        }
    }

    fun updateFood(foodUi: FoodUi) {
        viewModelScope.launch {
            try {
                val food = foodUi.mapFoodUiToFood()
                updateFoodUseCase.update(food = food)
            } catch (e: Exception) {
                Log.e(TAG, "exception within setFoodQuantity: $e")
                //TODO display error to user
            }
        }
    }

    private fun FoodUi.mapFoodUiToFood(): Food =
        with(foodUiMapper) {
            this@mapFoodUiToFood.toFood()
        }

    private fun handleMappingErrorToUiModel(error: ResultOf.Error) =
        ResultOf.Error(message = error.message, exception = error.exception)

    private fun handleMappingSuccessToUiModel(
        models: ResultOf.Success<List<Food>>,
        context: Context
    ) = ResultOf.Success(data = models.data.map { food ->
        with(foodUiMapper) {
            food.toFoodUi(context = context)
        }
    })
}