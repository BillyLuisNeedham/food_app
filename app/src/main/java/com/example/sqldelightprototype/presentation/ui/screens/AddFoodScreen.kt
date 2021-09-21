package com.example.sqldelightprototype.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.data.utils.time.TimeManager
import com.example.sqldelightprototype.data.utils.time.TimeManagerImpl
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.presentation.models.ErrorState
import com.example.sqldelightprototype.presentation.ui.components.ButtonBase
import com.example.sqldelightprototype.presentation.ui.components.TextInput
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@ExperimentalComposeUiApi
@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier,
    addFood: (Food) -> Unit,
    navigateBack: () -> Unit,
    uploadState: ResultOf<Unit>?,
    timeManager: TimeManager
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (expiryInDays, setExpiryInDays) = remember { mutableStateOf("") }
    val (error, setError) = remember { mutableStateOf(false) }
    val (nameError, setNameError) = remember { mutableStateOf(ErrorState(error = false)) }
    val (amountError, setAmountError) = remember { mutableStateOf(ErrorState(error = false)) }
    val (expiryError, setExpiryError) = remember { mutableStateOf(ErrorState(error = false)) }
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
                IconButton(onClick = navigateBack) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_navigation_content_description)
                    )
                }
            },

            )
    }) {
        AddFoodScreenContent(
            modifier = modifier,
            name = name,
            setName = setName,
            amount = amount,
            setAmount = setAmount,
            expiryInDays = expiryInDays,
            setExpiryInDays = setExpiryInDays,
            keyboardController = keyboardController,
            addFood = addFood,
            setError = setError,
            timeManager = timeManager,
            error = error,
            nameError = nameError,
            amountError = amountError,
            expiryError = expiryError,
            setNameError = setNameError,
            setAmountError = setAmountError,
            setExpiryError = setExpiryError,
        )
    }

}

@ExperimentalComposeUiApi
@Composable
fun AddFoodScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    setName: (String) -> Unit,
    nameError: ErrorState,
    amount: String,
    setAmount: (String) -> Unit,
    amountError: ErrorState,
    expiryInDays: String,
    setExpiryInDays: (String) -> Unit,
    expiryError: ErrorState,
    keyboardController: SoftwareKeyboardController?,
    addFood: (Food) -> Unit,
    setError: (Boolean) -> Unit,
    timeManager: TimeManager,
    error: Boolean,
    setNameError: (ErrorState) -> Unit,
    setAmountError: (ErrorState) -> Unit,
    setExpiryError: (ErrorState) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TextInputs(
                modifier = Modifier
                    .padding(top = 72.dp),
                name = name,
                setName = setName,
                amount = amount,
                setAmount = setAmount,
                expiryInDays = expiryInDays,
                setExpiryInDays = setExpiryInDays,
                onDone = {
                    onAddFood(
                        name = name,
                        amount = amount,
                        keyboardController = keyboardController,
                        addFood = addFood,
                        showError = setError,
                        expiryInDaysFromNow = expiryInDays,
                        timeManager = timeManager,
                        setNameError = setNameError,
                        setAmountError = setAmountError,
                        setExpiryError = setExpiryError,
                    )
                },
                nameError = nameError,
                amountError = amountError,
                expiryError = expiryError
            )
            ButtonBase(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                onClick = {
                    onAddFood(
                        name = name,
                        amount = amount,
                        keyboardController = keyboardController,
                        addFood = addFood,
                        showError = setError,
                        expiryInDaysFromNow = expiryInDays,
                        timeManager = timeManager,
                        setNameError = setNameError,
                        setAmountError = setAmountError,
                        setExpiryError = setExpiryError,
                    )
                },
            ) {
                Text(text = stringResource(R.string.add_food))
            }
        }
        if (error) {
            Snackbar {
                Text(text = stringResource(R.string.error_adding_food))
            }
        }
    }
}

private fun handleActionsOnUploadState(
    uploadState: ResultOf<Unit>?,
    onError: () -> Unit,
    onSuccess: () -> Unit
) {
    when (uploadState) {
        is ResultOf.Error -> {
            onError()
        }
        ResultOf.Loading -> {
            // TODO write
        }
        is ResultOf.Success -> {
            onSuccess()
        }
    }
}

@ExperimentalComposeUiApi
private fun onAddFood(
    name: String,
    amount: String,
    expiryInDaysFromNow: String,
    keyboardController: SoftwareKeyboardController?,
    addFood: (Food) -> Unit,
    showError: (Boolean) -> Unit,
    timeManager: TimeManager,
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
        expiryInDaysFromNow.isEmpty() -> setExpiryError(
            ErrorState(
                error = true,
                messageResource = R.string.error_blank_expiry
            )
        )
        else -> {
            val expirationDate =
                timeManager.getCurrentTimeStampWithDaysAdded(days = expiryInDaysFromNow.toLong())

            showError(false)
            keyboardController?.hide()
            addFood(
                Food(
                    name = name,
                    quantity = amount.toInt(),
                    expirationDate = expirationDate
                )
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun TextInputs(
    modifier: Modifier = Modifier,
    name: String,
    setName: (String) -> Unit,
    nameError: ErrorState,
    amount: String,
    setAmount: (String) -> Unit,
    amountError: ErrorState,
    onDone: () -> Unit,
    expiryInDays: String,
    setExpiryInDays: (String) -> Unit,
    expiryError: ErrorState
) {
    val (focusRequesterAmount, focusRequesterExpiry) = FocusRequester.createRefs()

    Column(modifier = modifier) {
        TextInput(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(R.string.name),
            text = name,
            onTextChange = setName,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = true,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusRequesterAmount.requestFocus() }
            ),
            errorState = nameError
        )

        Row(modifier = Modifier.padding(top = 12.dp)) {
            TextInput(
                modifier = Modifier
                    .focusRequester(focusRequesterAmount)
                    .padding(end = 4.dp)
                    .weight(1f),
                label = stringResource(R.string.amount),
                text = amount,
                onTextChange = setAmount,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequesterExpiry.requestFocus()
                    }
                ),
                errorState = amountError
            )
            TextInput(
                modifier = Modifier
                    .focusRequester(focusRequesterExpiry)
                    .padding(start = 4.dp)
                    .weight(1f),
                label = stringResource(R.string.expires_days_from_now),
                text = expiryInDays,
                onTextChange = setExpiryInDays,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusRequesterExpiry.freeFocus()
                        onDone()
                    }
                ),
                errorState = expiryError
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