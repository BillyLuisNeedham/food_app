package com.example.sqldelightprototype.presentation.ui.screens.foodlistscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightprototype.data.utils.extensionfunctions.capitaliseStrings
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.domain.usecases.food.DeleteAllFoodsUseCase
import com.example.sqldelightprototype.domain.usecases.food.DeleteFoodUseCase
import com.example.sqldelightprototype.domain.usecases.food.GetAllFoodsSortedByAmountUseCase
import com.example.sqldelightprototype.domain.usecases.food.GetAllFoodsSortedByExpiryUseCase
import com.example.sqldelightprototype.domain.usecases.food.GetAllFoodsSortedByNameUseCase
import com.example.sqldelightprototype.domain.usecases.user.GetAllUsersUseCase
import com.example.sqldelightprototype.domain.usecases.food.UpdateFoodUseCase
import com.example.sqldelightprototype.domain.usecases.user.DeleteUserUseCase
import com.example.sqldelightprototype.presentation.mappers.FoodUiMapper
import com.example.sqldelightprototype.presentation.models.FoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodListScreenViewModel @Inject constructor(
    private val getAllFoodsSortedByNameUseCase: GetAllFoodsSortedByNameUseCase,
    private val getAllFoodsSortedByExpiryUseCase: GetAllFoodsSortedByExpiryUseCase,
    private val getAllFoodsSortedByAmountUseCase: GetAllFoodsSortedByAmountUseCase,
    private val deleteFoodUseCase: DeleteFoodUseCase,
    private val updateFoodUseCase: UpdateFoodUseCase,
    private val deleteAllFoodsUseCase: DeleteAllFoodsUseCase,
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
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

    private val _state = MutableStateFlow<ResultOf<Unit>>(ResultOf.Success(data = Unit))
    val state: StateFlow<ResultOf<Unit>>
        get() = _state

    private val _foodList = MutableStateFlow(listOf<FoodUi>())
    val foodList: StateFlow<List<FoodUi>>
        get() = _foodList

    private val _userList = MutableStateFlow(listOf<User>())
    val userList: StateFlow<List<User>>
        get() = _userList

    private val _selectedUsers = MutableStateFlow(listOf<User>())
    val selectedUsers: StateFlow<List<User>>
        get() = _selectedUsers

    init {
        getAllFoods()
        getAllUsers()
    }

    fun getAllFoods(
        sortBy: SortFoods = SortFoods.ByName
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

    fun addOrRemoveUserIdToSelectedUserIds(user: User) {
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

    private fun deleteUser(user: User) {
        runUseCase {
            deleteUserUseCase.delete(user)
        }
    }

    private fun removeUserIdFromSelectedUserIds(user: User) {
        _selectedUsers.value =
            _selectedUsers.value.filterNot { it == user }
    }

    private fun addUserIdToSelectedUserIds(user: User) {
        _selectedUsers.value = _selectedUsers.value.plus(user)
    }

    private fun getAllUsers() {
        viewModelScope.launch {
            val users =
                getAllUsersUseCase.get().map {
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
        getFoodCallback: () -> Flow<ResultOf<List<Food>>>
    ) = getFoodCallback().map {
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
                )
            }
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
    ) = models.data.map { food ->
        with(foodUiMapper) {
            food.toFoodUi()
        }
    }
}

