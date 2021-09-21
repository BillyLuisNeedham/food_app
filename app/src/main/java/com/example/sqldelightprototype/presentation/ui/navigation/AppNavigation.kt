package com.example.sqldelightprototype.presentation.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.FoodListScreen.route
    ) {
        addFoodScreen(navController = navController)
        foodListScreen(
            navController = navController,
            context = context
        )
    }
}

private fun NavGraphBuilder.foodListScreen(
    navController: NavHostController,
    context: Context
) {
    composable(Screens.FoodListScreen.route) {

        val viewModel: FoodListScreenViewModel = hiltViewModel()
        val foodList =
            viewModel.getAllFoods(context = context)
                .collectAsState(listOf())
        val screenState = viewModel.state.collectAsState()

        FoodListScreen(
            foodList = foodList.value,
            onClickFab = {
                navController.navigate(Screens.AddFoodScreen.route)
            },
            setFoodQuantity = {
                viewModel.updateFood(foodUi = it)
            },
            deleteFood = {
                viewModel.deleteFood(foodUi = it)
            },
            screenState = screenState.value,
            deleteAllFoods = {TODO()}
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

