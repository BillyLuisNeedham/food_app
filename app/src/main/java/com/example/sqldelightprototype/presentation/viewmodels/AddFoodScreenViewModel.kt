package com.example.sqldelightprototype.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.usecases.AddFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFoodScreenViewModel @Inject constructor(
    private val addFoodUseCase: AddFoodUseCase
) : ViewModel() {

    fun addFood(food: Food): ResultOf<Unit> {
        var result: ResultOf<Unit> = ResultOf.Loading
        viewModelScope.launch {
            result = addFoodUseCase.add(food = food)
        }
        return result
    }
}