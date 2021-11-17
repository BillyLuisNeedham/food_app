package com.example.sqldelightprototype.presentation.ui.navigation

sealed class Screens(val route: String) {
    object FoodListScreen : Screens("foodList")
    object AddFoodScreen : Screens("addFood")
    object AddUserScreen : Screens("addUser")
}
