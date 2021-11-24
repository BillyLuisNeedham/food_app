package com.example.sqldelightprototype.presentation.ui.screens.addfoodscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightprototype.data.utils.time.TimeManager
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.usecases.food.AddFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddFoodScreenViewModel @Inject constructor(
    private val addFoodUseCase: AddFoodUseCase,
    val timeManager: TimeManager
) : ViewModel() {

    private val _uploadState: MutableStateFlow<ResultOf<Unit>?> = MutableStateFlow(null)
    val uploadState: StateFlow<ResultOf<Unit>?> = _uploadState

    fun addFood(food: Food) {
        viewModelScope.launch {
            _uploadState.value = ResultOf.Loading
            _uploadState.value = addFoodUseCase.add(food = food)
        }
    }
}
