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
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.presentation.ui.components.ButtonBase
import com.example.sqldelightprototype.presentation.ui.components.TextInput
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@ExperimentalComposeUiApi
@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier,
    addFood: (Food) -> ResultOf<Unit>,
    navigateBack: () -> Unit
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    val (error, setError) = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            TextInputRow(
                modifier = Modifier
                    .padding(top = 72.dp),
                name = name,
                setName = setName,
                amount = amount,
                setAmount = setAmount,
                onDone = {
                    onDone(
                        name = name,
                        amount = amount,
                        keyboardController = keyboardController,
                        addFood = addFood,
                        navigateBack = navigateBack,
                        showError = setError
                    )
                }
            )
            ButtonBase(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                onClick = {
                    onDone(
                        name = name,
                        amount = amount,
                        keyboardController = keyboardController,
                        addFood = addFood,
                        navigateBack = navigateBack,
                        showError = setError
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

@ExperimentalComposeUiApi
private fun onDone(
    name: String,
    amount: String,
    keyboardController: SoftwareKeyboardController?,
    addFood: (Food) -> ResultOf<Unit>,
    navigateBack: () -> Unit,
    showError: (Boolean) -> Unit
) {
    showError(false)
    keyboardController?.hide()
    val result = addFood(
        Food(
            name = name,
            quantity = amount.toInt()
        )
    )
    when (result) {
        is ResultOf.Error -> {
            showError(true)
        }
        ResultOf.Loading -> TODO()
        is ResultOf.Success -> {
            navigateBack()
        }
    }
}

@ExperimentalComposeUiApi
@Composable
private fun TextInputRow(
    modifier: Modifier = Modifier,
    name: String,
    setName: (String) -> Unit,
    amount: String,
    setAmount: (String) -> Unit,
    onDone: () -> Unit,
) {
    val (focusRequester) = FocusRequester.createRefs()

    Row(
        modifier = modifier
    ) {
        TextInput(
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(3f),
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
                onNext = { focusRequester.requestFocus() }
            )
        )
        TextInput(
            modifier = Modifier
                .focusRequester(focusRequester)
                .weight(1f),
            label = stringResource(R.string.amount),
            text = amount,
            onTextChange = setAmount,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusRequester.freeFocus()
                    onDone()
                }
            )
        )
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun AddFoodScreenPreview() {
    SqlDelightPrototypeTheme {
        AddFoodScreen(
            addFood = { ResultOf.Success(data = Unit) },
            navigateBack = {}
        )
    }
}