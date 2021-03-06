package com.billyluisneedham.foodapp.presentation.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.billyluisneedham.foodapp.presentation.ui.screens.addfoodscreen.AddFoodScreen
import com.billyluisneedham.foodapp.presentation.ui.screens.addfoodscreen.AddFoodScreenViewModel
import com.billyluisneedham.foodapp.presentation.ui.screens.adduserscreen.AddUserScreen
import com.billyluisneedham.foodapp.presentation.ui.screens.adduserscreen.AddUserScreenViewModel
import com.billyluisneedham.foodapp.presentation.ui.screens.foodlistscreen.FoodListScreen
import com.billyluisneedham.foodapp.presentation.ui.screens.foodlistscreen.FoodListScreenViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
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
        foodListScreen(
            navController = navController,
        )
        addUserScreen(navController = navController)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.foodListScreen(
    navController: NavHostController,
) {
    composable(Screens.FoodListScreen.route) {

        val viewModel: FoodListScreenViewModel = hiltViewModel()
        val foodList =
            viewModel.foodList.collectAsState(listOf())
        val screenState = viewModel.state.collectAsState()
        val userList =
            viewModel.userList.collectAsState()
        val selectedUsers = viewModel.selectedUsers.collectAsState()

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
            deleteAllFoods = {
                viewModel.deleteAllFoods()
            },
            setFoodListSort = viewModel::getAllFoods,
            navigateToAddUserScreen = {
                navController.navigate(
                    Screens.AddUserScreen.route
                )
            },
            userList = userList.value,
            selectedUsers = selectedUsers.value,
            onLongPressUserListItem = viewModel::addOrRemoveUserIdToSelectedUserIds,
            clearSelectedUserIds = viewModel::clearSelectedUserIds,
            deleteAllSelectedUsers = viewModel::deleteAllSelectedUsers
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

private fun NavGraphBuilder.addUserScreen(navController: NavHostController) {
    composable(Screens.AddUserScreen.route) {

        val viewModel: AddUserScreenViewModel = hiltViewModel()
        val screenState = viewModel.screenState.collectAsState()

        AddUserScreen(
            screenState = screenState.value,
            navigateBack = { navController.navigateUp() },
            addUser = viewModel::addUser
        )
    }
}
