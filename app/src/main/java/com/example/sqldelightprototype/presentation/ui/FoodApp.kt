package com.example.sqldelightprototype.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sqldelightprototype.FoodData
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.presentation.ui.navigation.AppNavigation
import com.example.sqldelightprototype.presentation.ui.screens.AddFoodScreen
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@ExperimentalComposeUiApi
@Composable
fun FoodApp() {
    val navController = rememberNavController()

    SqlDelightPrototypeTheme {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
        ) {
            AppNavigation(navController = navController)
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun FoodAppPreview() {
    FoodApp()
}