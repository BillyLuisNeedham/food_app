package com.billyluisneedham.foodapp.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.billyluisneedham.foodapp.presentation.ui.navigation.AppNavigation
import com.billyluisneedham.foodapp.presentation.ui.theme.SqlDelightPrototypeTheme

@ExperimentalFoundationApi
@ExperimentalMaterialApi
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
