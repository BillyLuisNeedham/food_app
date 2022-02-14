package com.billyluisneedham.foodapp.presentation.ui.screens.addfoodscreen

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.billyluisneedham.foodapp.R
import com.billyluisneedham.foodapp.data.utils.time.TimeManager
import com.billyluisneedham.foodapp.data.utils.time.TimeManagerImpl
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.Food
import com.billyluisneedham.foodapp.presentation.models.ErrorState
import com.billyluisneedham.foodapp.presentation.ui.components.NavigateBackButton
import com.billyluisneedham.foodapp.presentation.ui.theme.SqlDelightPrototypeTheme

@ExperimentalComposeUiApi
@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier,
    addFood: (com.billyluisneedham.foodapp.domain.models.Food) -> Unit,
    navigateBack: () -> Unit,
    uploadState: com.billyluisneedham.foodapp.domain.ResultOf<Unit>?,
    timeManager: TimeManager
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (expiresText, setExpiresText) = remember { mutableStateOf("") }
    val (expiryTimeStamp, setExpiryTimeStamp) = remember { mutableStateOf(0L) }
    val (error, setError) = remember { mutableStateOf(false) }
    val (nameError, setNameError) = remember { mutableStateOf(ErrorState(error = false)) }
    val (amountError, setAmountError) = remember { mutableStateOf(ErrorState(error = false)) }
    val (expiryError, setExpiryError) = remember { mutableStateOf(ErrorState(error = false)) }
    val (showDatePicker, setShowDatePicker) = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = uploadState) {
        handleActionsOnUploadState(
            uploadState = uploadState,
            onError = { setError(true) },
            onSuccess = navigateBack
        )
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(stringResource(R.string.add_food_title))
            },
            navigationIcon = {
                NavigateBackButton(onClick = navigateBack)
            },

        )
    }) {
        AddFoodScreenContent(
            modifier = modifier,
            name = name,
            setName = setName,
            nameError = nameError,
            amount = amount,
            setAmount = setAmount,
            amountError = amountError,
            expiryInDays = expiresText,
            expiryError = expiryError,
            keyboardController = keyboardController,
            addFood = {
                onAddFood(
                    name = name,
                    amount = amount,
                    expiryTimeStamp = expiryTimeStamp,
                    keyboardController = keyboardController,
                    addFood = addFood,
                    showError = setError,
                    setNameError = setNameError,
                    setAmountError = setAmountError,
                    setExpiryError = setExpiryError
                )
            },
            error = error,
            showDatePicker = showDatePicker,
            setShowDatePicker = setShowDatePicker,
            setSelectedDate = { year, month, day ->
                val time = timeManager.getTimeStampForCurrentLocaleFrom(
                    year = year,
                    month = month,
                    day = day
                )
                setExpiryTimeStamp(time)
                setExpiresText("$day-${month + 1}-$year")
            }
        )
    }
}

private fun handleActionsOnUploadState(
    uploadState: com.billyluisneedham.foodapp.domain.ResultOf<Unit>?,
    onError: () -> Unit,
    onSuccess: () -> Unit
) {
    when (uploadState) {
        is com.billyluisneedham.foodapp.domain.ResultOf.Error -> {
            onError()
        }
        com.billyluisneedham.foodapp.domain.ResultOf.Loading -> {
            // TODO write
        }
        is com.billyluisneedham.foodapp.domain.ResultOf.Success -> {
            onSuccess()
        }
    }
}

@ExperimentalComposeUiApi
private fun onAddFood(
    name: String,
    amount: String,
    expiryTimeStamp: Long,
    keyboardController: SoftwareKeyboardController?,
    addFood: (com.billyluisneedham.foodapp.domain.models.Food) -> Unit,
    showError: (Boolean) -> Unit,
    setNameError: (ErrorState) -> Unit,
    setAmountError: (ErrorState) -> Unit,
    setExpiryError: (ErrorState) -> Unit
) {
    setNameError(ErrorState(error = false))
    setAmountError(ErrorState(error = false))
    setExpiryError(ErrorState(error = false))
    when {
        name.isEmpty() -> setNameError(
            ErrorState(
                error = true,
                messageResource = R.string.error_blank_name
            )
        )

        amount.isEmpty() -> setAmountError(
            ErrorState(
                error = true,
                messageResource = R.string.error_blank_amount
            )
        )
        expiryTimeStamp == 0L -> setExpiryError(
            ErrorState(
                error = true,
                messageResource = R.string.error_blank_expiry
            )
        )
        else -> {
            showError(false)
            keyboardController?.hide()
            addFood(
                com.billyluisneedham.foodapp.domain.models.Food(
                    name = name,
                    quantity = amount.toInt(),
                    expirationDate = expiryTimeStamp
                )
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun AddFoodScreenPreview() {
    SqlDelightPrototypeTheme {
        AddFoodScreen(
            addFood = { TODO() },
            navigateBack = {},
            uploadState = null,
            timeManager = TimeManagerImpl()
        )
    }
}
