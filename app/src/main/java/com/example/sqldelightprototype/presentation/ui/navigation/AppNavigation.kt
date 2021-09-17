package com.example.sqldelightprototype.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.presentation.ui.screens.AddFoodScreen
import com.example.sqldelightprototype.presentation.ui.screens.FoodListScreen
import com.example.sqldelightprototype.presentation.viewmodels.AddFoodScreenViewModel
import com.example.sqldelightprototype.presentation.viewmodels.FoodListScreenViewModel

sealed class Screens(val route: String) {
    object FoodListScreen : Screens("foodList")
    object AddFoodScreen : Screens("addFood")
}

@ExperimentalComposeUiApi
@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screens.FoodListScreen.route
    ) {
        addFoodScreen(navController = navController)
        foodListScreen(navController = navController)
    }
}

private fun NavGraphBuilder.foodListScreen(navController: NavHostController) {
    composable(Screens.FoodListScreen.route) {

        val viewModel: FoodListScreenViewModel = hiltViewModel()
        val foodList = viewModel.getAllFoods().collectAsState(ResultOf.Success(listOf()))

        FoodListScreen(
            foodList = foodList.value,
            onClickFab = {
                navController.navigate(Screens.AddFoodScreen.route)
            }
        )
    }
}

@ExperimentalComposeUiApi
private fun NavGraphBuilder.addFoodScreen(navController: NavHostController) {
    composable(Screens.AddFoodScreen.route) {

        val viewModel: AddFoodScreenViewModel = hiltViewModel()
        val uploadState = viewModel.uploadState.collectAsState(null)
        AddFoodScreen(
            addFood = viewModel::addFood,
            uploadState = uploadState.value,
            navigateBack = { navController.navigateUp() },
            timeManager = viewModel.timeManager
        )
    }
}

