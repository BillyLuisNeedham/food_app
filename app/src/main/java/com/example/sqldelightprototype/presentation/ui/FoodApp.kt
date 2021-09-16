package com.example.sqldelightprototype.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.sqldelightprototype.presentation.ui.navigation.AppNavigation
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