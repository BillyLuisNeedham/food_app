package com.example.sqldelightprototype.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.Food
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun AddFoodScreen(modifier: Modifier = Modifier, addFood: (Food) -> ResultOf<Unit>) {
    val (text, setText) = remember { mutableStateOf("") }
}

@Preview
@Composable
fun AddFoodScreenPreview() {
    SqlDelightPrototypeTheme {
        AddFoodScreen(addFood = { ResultOf.Success(data = Unit) })
    }
}