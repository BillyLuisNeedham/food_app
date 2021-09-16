package com.example.sqldelightprototype.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sqldelightprototype.domain.usecases.GetAllFoodsUseCase
import javax.inject.Inject

class FoodListScreenViewModel @Inject constructor(
    private val getAllFoodsUseCase: GetAllFoodsUseCase
): ViewModel() {

    fun getAllFoods() = getAllFoodsUseCase.get()
}