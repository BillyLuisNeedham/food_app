package com.example.sqldelightprototype.presentation.ui.screens.adduserscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.domain.usecases.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AddUserScreenViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "AddUserScreenViewModel"
    }

    private val _screenState = MutableStateFlow<ResultOf<Unit>>(ResultOf.Success(data = Unit))
    val screenState: StateFlow<ResultOf<Unit>>
        get() = _screenState

    suspend fun addUser(user: User) {
        viewModelScope.launch {
            try {
                _screenState.value = ResultOf.Loading
                _screenState.value = addUserUseCase.add(user = user)
            } catch (e: Exception) {
                Log.e(TAG, "exception within addUser: $e")
                _screenState.value = ResultOf.Error(exception = e)
            }
        }
    }
}