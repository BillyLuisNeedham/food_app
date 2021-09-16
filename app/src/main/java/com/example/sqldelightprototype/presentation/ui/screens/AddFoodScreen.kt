package com.example.sqldelightprototype.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.presentation.ui.components.TextInput
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun AddFoodScreen(modifier: Modifier = Modifier, addFood: (Food) -> ResultOf<Unit>) {
    val (name, setName) = remember { mutableStateOf("") }
    val (amount, setAmount) = remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize()
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(top = 72.dp)
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
                )
            )
            TextInput(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.amount),
                text = amount,
                onTextChange = setAmount,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun AddFoodScreenPreview() {
    SqlDelightPrototypeTheme {
        AddFoodScreen(addFood = { ResultOf.Success(data = Unit) })
    }
}