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
    private val addUserUseCase: com.billyluisneedham.foodapp.domain.usecases.user.AddUserUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "AddUserScreenViewModel"
    }

    private val _screenState = MutableStateFlow<com.billyluisneedham.foodapp.domain.ResultOf<Unit>?>(null)
    val screenState: StateFlow<com.billyluisneedham.foodapp.domain.ResultOf<Unit>?>
        get() = _screenState

    fun addUser(user: com.billyluisneedham.foodapp.domain.models.User) {
        viewModelScope.launch {
            try {
                _screenState.value = com.billyluisneedham.foodapp.domain.ResultOf.Loading
                _screenState.value = addUserUseCase.add(user = user)
            } catch (e: Exception) {
                Log.e(TAG, "exception within addUser: $e")
                _screenState.value = com.billyluisneedham.foodapp.domain.ResultOf.Error(exception = e)
            }
        }
    }
}
