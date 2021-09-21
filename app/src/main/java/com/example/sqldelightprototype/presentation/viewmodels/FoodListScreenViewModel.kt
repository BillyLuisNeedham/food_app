package com.example.sqldelightprototype.presentation.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.usecases.DeleteAllFoodsUseCase
import com.example.sqldelightprototype.domain.usecases.DeleteFoodUseCase
import com.example.sqldelightprototype.domain.usecases.GetAllFoodsUseCase
import com.example.sqldelightprototype.domain.usecases.UpdateFoodUseCase
import com.example.sqldelightprototype.presentation.mappers.FoodUiMapper
import com.example.sqldelightprototype.presentation.models.FoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FoodListScreenViewModel @Inject constructor(
    private val getAllFoodsUseCase: GetAllFoodsUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase,
    private val updateFoodUseCase: UpdateFoodUseCase,
    private val deleteAllFoodsUseCase: DeleteAllFoodsUseCase,
    private val foodUiMapper: FoodUiMapper
) : ViewModel() {

    companion object {
        private const val TAG = "FoodListScreenViewModel"
    }

    private val _state = MutableStateFlow<ResultOf<Unit>>(ResultOf.Success(data = Unit))
    val state: StateFlow<ResultOf<Unit>>
        get() = _state

    fun getAllFoods(context: Context) = getAllFoodsUseCase.get().map {
        when (it) {
            is ResultOf.Error -> {
                _state.value = it
                listOf()
            }
            ResultOf.Loading -> {
                _state.value = ResultOf.Loading
                listOf()
            }
            is ResultOf.Success -> {
                _state.value = ResultOf.Success(data = Unit)
                mapSuccessToUiModel(
                    models = it,
                    context = context
                )
            }
        }
    }

    fun updateFood(foodUi: FoodUi) {
        runUseCase {
            val food = foodUi.mapFoodUiToFood()
            updateFoodUseCase.update(food = food)
        }
    }


    fun deleteFood(foodUi: FoodUi) {
        runUseCase {
            val food = foodUi.mapFoodUiToFood()
            deleteFoodUseCase.delete(food = food)
        }
    }

    fun deleteAllFoods() {
        runUseCase {
            deleteAllFoodsUseCase.deleteAll()
        }
    }

    private fun runUseCase(callback: suspend () -> ResultOf<Unit>) {
        viewModelScope.launch {
            try {
                _state.value = ResultOf.Loading

                val result = callback()
                _state.value = result

            } catch (e: Exception) {
                Log.e(TAG, "exception within runUseCase: $e")
                _state.value = ResultOf.Error(exception = e)
            }
        }
    }

    private fun FoodUi.mapFoodUiToFood(): Food =
        with(foodUiMapper) {
            this@mapFoodUiToFood.toFood()
        }

    private fun mapSuccessToUiModel(
        models: ResultOf.Success<List<Food>>,
        context: Context
    ) = models.data.map { food ->
        with(foodUiMapper) {
            food.toFoodUi(context = context)
        }
    }
}