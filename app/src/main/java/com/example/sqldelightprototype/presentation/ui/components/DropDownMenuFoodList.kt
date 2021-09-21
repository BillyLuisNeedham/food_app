package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.presentation.viewmodels.FoodListScreenViewModel

@Composable
fun DropdownMenuFoodList(
    showMenu: Boolean,
    setShowMenu: (Boolean) -> Unit,
    setShowDeleteAllWarning: (Boolean) -> Unit,
    setFoodListSort: (FoodListScreenViewModel.Companion.SortFoods) -> Unit
) {
    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { setShowMenu(false) }
    ) {
        DropdownMenuItem(onClick = {
            setShowMenu(false)
            setShowDeleteAllWarning(true)
        }) {
            Text(stringResource(R.string.delete_all_foods))
        }
        DropdownMenuItem(onClick = {
            setShowMenu(false)
            setFoodListSort(FoodListScreenViewModel.Companion.SortFoods.ByName)
        }) {
            Text(stringResource(R.string.sort_food_by_name))
        }
        DropdownMenuItem(onClick = {
            setShowMenu(false)
            setFoodListSort(FoodListScreenViewModel.Companion.SortFoods.ByExpiry)
        }) {
            Text(stringResource(R.string.sort_food_by_expiry))
        }
        DropdownMenuItem(onClick = {
            setShowMenu(false)
            setFoodListSort(FoodListScreenViewModel.Companion.SortFoods.ByAmount)
        }) {
            Text(stringResource(R.string.sort_food_by_amount))
        }
    }
}