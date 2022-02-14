package com.billyluisneedham.foodapp.presentation.ui.screens.addfoodscreen

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.billyluisneedham.foodapp.R
import com.billyluisneedham.foodapp.presentation.models.ErrorState
import com.billyluisneedham.foodapp.presentation.ui.components.ButtonBase
import com.billyluisneedham.foodapp.presentation.ui.components.DatePickerDialogBase
import com.billyluisneedham.foodapp.presentation.ui.components.TextInput

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
    expiryError: ErrorState,
    keyboardController: SoftwareKeyboardController?,
    addFood: () -> Unit,
    error: Boolean,
    showDatePicker: Boolean,
    setShowDatePicker: (Boolean) -> Unit,
    setSelectedDate: (year: Int, month: Int, day: Int) -> Unit
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
                expires = expiryInDays,
                keyboardController = keyboardController,
                nameError = nameError,
                amountError = amountError,
                expiryError = expiryError,
                showDatePicker = showDatePicker,
                setShowDatePicker = setShowDatePicker,
                setSelectedDate = setSelectedDate
            )
            ButtonBase(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                onClick = {
                    addFood()
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
    expires: String,
    expiryError: ErrorState,
    keyboardController: SoftwareKeyboardController?,
    showDatePicker: Boolean,
    setShowDatePicker: (Boolean) -> Unit,
    setSelectedDate: (year: Int, month: Int, day: Int) -> Unit
) {
    val (focusRequesterAmount, focusRequesterExpiry) = FocusRequester.createRefs()
    val interactionSource = remember { MutableInteractionSource() }

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
                    .onFocusChanged {
                        if (it.isFocused) {
                            keyboardController?.hide()
                            setShowDatePicker(true)
                        }
                    }
                    .padding(start = 4.dp)
                    .weight(1f),
                interactionSource = interactionSource,
                label = stringResource(R.string.expires),
                text = expires,
                onTextChange = {},
                errorState = expiryError,
                readOnly = true
            )
        }
        DatePickerDialogBase(
            showDialog = showDatePicker,
            setShowDialog = setShowDatePicker,
            setDate = { year: Int, month: Int, day: Int ->
                setSelectedDate(year, month, day)
            }
        )
    }
}
