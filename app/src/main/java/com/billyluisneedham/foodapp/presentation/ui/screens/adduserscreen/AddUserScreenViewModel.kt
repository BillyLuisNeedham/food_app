package com.billyluisneedham.foodapp.presentation.ui.screens.adduserscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.User
import com.billyluisneedham.foodapp.domain.usecases.user.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddUserScreenViewModel @Inject constructor(
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "AddUserScreenViewModel"
    }

    private val _screenState = MutableStateFlow<ResultOf<Unit>?>(null)
    val screenState: StateFlow<ResultOf<Unit>?>
        get() = _screenState

    fun addUser(user: User) {
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
