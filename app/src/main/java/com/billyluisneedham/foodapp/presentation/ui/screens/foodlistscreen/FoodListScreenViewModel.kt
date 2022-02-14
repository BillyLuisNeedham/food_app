package com.billyluisneedham.foodapp.presentation.ui.screens.foodlistscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.domain.models.User
import com.billyluisneedham.foodapp.domain.usecases.food.DeleteAllFoodsUseCase
import com.billyluisneedham.foodapp.domain.usecases.food.DeleteFoodUseCase
import com.billyluisneedham.foodapp.domain.usecases.food.GetAllFoodsSortedByAmountUseCase
import com.billyluisneedham.foodapp.domain.usecases.food.GetAllFoodsSortedByExpiryUseCase
import com.billyluisneedham.foodapp.domain.usecases.food.GetAllFoodsSortedByNameUseCase
import com.billyluisneedham.foodapp.domain.usecases.food.UpdateFoodUseCase
import com.billyluisneedham.foodapp.domain.usecases.user.DeleteUserUseCase
import com.billyluisneedham.foodapp.domain.usecases.user.GetAllUsersUseCase
import com.billyluisneedham.foodapp.presentation.mappers.FoodUiMapper
import com.billyluisneedham.foodapp.presentation.models.FoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel
class FoodListScreenViewModel @Inject constructor(
    private val getAllFoodsSortedByNameUseCase: com.billyluisneedham.foodapp.domain.usecases.food.GetAllFoodsSortedByNameUseCase,
    private val getAllFoodsSortedByExpiryUseCase: com.billyluisneedham.foodapp.domain.usecases.food.GetAllFoodsSortedByExpiryUseCase,
    private val getAllFoodsSortedByAmountUseCase: com.billyluisneedham.foodapp.domain.usecases.food.GetAllFoodsSortedByAmountUseCase,
    private val deleteFoodUseCase: com.billyluisneedham.foodapp.domain.usecases.food.DeleteFoodUseCase,
    private val updateFoodUseCase: com.billyluisneedham.foodapp.domain.usecases.food.UpdateFoodUseCase,
    private val deleteAllFoodsUseCase: com.billyluisneedham.foodapp.domain.usecases.food.DeleteAllFoodsUseCase,
    private val getAllUsersUseCase: com.billyluisneedham.foodapp.domain.usecases.user.GetAllUsersUseCase,
    private val deleteUserUseCase: com.billyluisneedham.foodapp.domain.usecases.user.DeleteUserUseCase,
    private val foodUiMapper: FoodUiMapper
) : ViewModel() {

    companion object {
        private const val TAG = "FoodListScreenViewModel"

        enum class SortFoods {
            ByName,
            ByExpiry,
            ByAmount
        }
    }

    private val _state = MutableStateFlow<com.billyluisneedham.foodapp.domain.ResultOf<Unit>>(com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit))
    val state: StateFlow<com.billyluisneedham.foodapp.domain.ResultOf<Unit>>
        get() = _state

    private val _foodList = MutableStateFlow(listOf<FoodUi>())
    val foodList: StateFlow<List<FoodUi>>
        get() = _foodList

    private val _userList = MutableStateFlow(listOf<com.billyluisneedham.foodapp.domain.models.User>())
    val userList: StateFlow<List<com.billyluisneedham.foodapp.domain.models.User>>
        get() = _userList

    private val _selectedUsers = MutableStateFlow(listOf<com.billyluisneedham.foodapp.domain.models.User>())
    val selectedUsers: StateFlow<List<com.billyluisneedham.foodapp.domain.models.User>>
        get() = _selectedUsers

    init {
        getAllFoods()
        getAllUsers()
    }

    fun getAllFoods(
        sortBy: SortFoods = SortFoods.ByExpiry
    ) {
        viewModelScope.launch {

            val foodMap = getFoodHandler {
                when (sortBy) {
                    SortFoods.ByName -> getAllFoodsSortedByNameUseCase.get()
                    SortFoods.ByExpiry -> getAllFoodsSortedByExpiryUseCase.get()
                    SortFoods.ByAmount -> getAllFoodsSortedByAmountUseCase.get()
                }
            }

            foodMap.collect {
                _foodList.value = it
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

    fun addOrRemoveUserIdToSelectedUserIds(user: com.billyluisneedham.foodapp.domain.models.User) {
        when (user) {
            in _selectedUsers.value ->
                removeUserIdFromSelectedUserIds(user = user)

            else -> addUserIdToSelectedUserIds(user = user)
        }
    }

    fun clearSelectedUserIds() {
        _selectedUsers.value = listOf()
    }

    fun deleteAllSelectedUsers() {
        _selectedUsers.value.forEach {
            deleteUser(user = it)
        }
    }

    private fun deleteUser(user: com.billyluisneedham.foodapp.domain.models.User) {
        runUseCase {
            deleteUserUseCase.delete(user)
        }
    }

    private fun removeUserIdFromSelectedUserIds(user: com.billyluisneedham.foodapp.domain.models.User) {
        _selectedUsers.value =
            _selectedUsers.value.filterNot { it == user }
    }

    private fun addUserIdToSelectedUserIds(user: com.billyluisneedham.foodapp.domain.models.User) {
        _selectedUsers.value = _selectedUsers.value.plus(user)
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            val users =
                getAllUsersUseCase.get().map {
                    when (it) {
                        is com.billyluisneedham.foodapp.domain.ResultOf.Error -> {
                            _state.value = it
                            listOf()
                        }
                        com.billyluisneedham.foodapp.domain.ResultOf.Loading -> {
                            _state.value = com.billyluisneedham.foodapp.domain.ResultOf.Loading
                            listOf()
                        }
                        is com.billyluisneedham.foodapp.domain.ResultOf.Success -> {
                            _state.value = com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit)
                            it.data
                        }
                    }
                }

            users.collect {
                _userList.value = it.map { user ->
                    user.capitaliseStrings()
                }
            }
        }
    }

    private fun getFoodHandler(
        getFoodCallback: () -> Flow<com.billyluisneedham.foodapp.domain.ResultOf<List<com.billyluisneedham.foodapp.domain.models.Food>>>
    ) = getFoodCallback().map {
        when (it) {
            is com.billyluisneedham.foodapp.domain.ResultOf.Error -> {
                _state.value = it
                listOf()
            }
            com.billyluisneedham.foodapp.domain.ResultOf.Loading -> {
                _state.value = com.billyluisneedham.foodapp.domain.ResultOf.Loading
                listOf()
            }
            is com.billyluisneedham.foodapp.domain.ResultOf.Success -> {
                _state.value = com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit)
                mapSuccessToUiModel(
                    models = it,
                )
            }
        }
    }

    private fun runUseCase(callback: suspend () -> com.billyluisneedham.foodapp.domain.ResultOf<Unit>) {
        viewModelScope.launch {
            try {
                _state.value = com.billyluisneedham.foodapp.domain.ResultOf.Loading

                val result = callback()
                _state.value = result
            } catch (e: Exception) {
                Log.e(TAG, "exception within runUseCase: $e")
                _state.value = com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)
            }
        }
    }

    private fun FoodUi.mapFoodUiToFood(): com.billyluisneedham.foodapp.domain.models.Food =
        with(foodUiMapper) {
            this@mapFoodUiToFood.toFood()
        }

    private fun mapSuccessToUiModel(
        models: com.billyluisneedham.foodapp.domain.ResultOf.Success<List<com.billyluisneedham.foodapp.domain.models.Food>>,
    ) = models.data.map { food ->
        with(foodUiMapper) {
            food.toFoodUi()
        }
    }
}
