package com.billyluisneedham.foodapp.presentation.ui.screens.addfoodscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billyluisneedham.foodapp.data.utils.time.TimeManager
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.domain.usecases.food.AddFoodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddFoodScreenViewModel @Inject constructor(
    private val addFoodUseCase: com.billyluisneedham.foodapp.domain.usecases.food.AddFoodUseCase,
    val timeManager: com.billyluisneedham.foodapp.data.utils.time.TimeManager
) : ViewModel() {

    private val _uploadState: MutableStateFlow<com.billyluisneedham.foodapp.domain.ResultOf<Unit>?> = MutableStateFlow(null)
    val uploadState: StateFlow<com.billyluisneedham.foodapp.domain.ResultOf<Unit>?> = _uploadState

    fun addFood(food: com.billyluisneedham.foodapp.domain.models.Food) {
        viewModelScope.launch {
            _uploadState.value = com.billyluisneedham.foodapp.domain.ResultOf.Loading
            _uploadState.value = addFoodUseCase.add(food = food)
        }
    }
}
