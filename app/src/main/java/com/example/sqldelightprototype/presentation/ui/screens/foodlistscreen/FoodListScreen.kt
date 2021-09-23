package com.example.sqldelightprototype.presentation.ui.screens.foodlistscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.presentation.models.FoodUi
import com.example.sqldelightprototype.presentation.ui.components.BottomAppBarBase
import com.example.sqldelightprototype.presentation.ui.components.DeleteDialog
import com.example.sqldelightprototype.presentation.ui.components.DropdownMenuFoodList
import com.example.sqldelightprototype.presentation.ui.components.ErrorUi
import com.example.sqldelightprototype.presentation.ui.components.LoadingUi
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun FoodListScreen(
    foodList: List<FoodUi>,
    onClickFab: () -> Unit,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
    screenState: ResultOf<Unit>,
    deleteAllFoods: () -> Unit,
    setFoodListSort: (FoodListScreenViewModel.Companion.SortFoods) -> Unit,
    navigateToAddUserScreen: () -> Unit,
) {
    val (showMenu, setShowMenu) = remember { mutableStateOf(false) }
    val (showDeleteAllWarning, setShowDeleteAllWarning) =
        remember { mutableStateOf(false) }
    val userDialogState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetState = userDialogState,
        sheetContent = {
            // TODO: 23/09/2021 Break into own component
            Column(
                modifier = Modifier
                    .defaultMinSize(minHeight = 200.dp)
            ) {
                Row {
                    Text(
                        stringResource(R.string.users),
                        style = MaterialTheme.typography.h6
                    )

                    IconButton(
                        onClick = {
                            coScope.launch {
                                userDialogState.hide()
                                navigateToAddUserScreen()
                            }
                        }
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = stringResource(
                                R.string.navigate_to_add_user_content_description
                            )
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                BottomAppBarBase(onClickPerson = {
                    coScope.launch {
                        userDialogState.show()
                    }
                })
            },
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.food_list_title))
                    },
                    actions = {
                        IconButton(onClick = { setShowMenu(!showMenu) }) {
                            Icon(
                                Icons.Filled.MoreVert,
                                contentDescription = stringResource(R.string.open_menu_content_description)
                            )
                        }
                        DropdownMenuFoodList(
                            showMenu = showMenu,
                            setShowMenu = setShowMenu,
                            setShowDeleteAllWarning = setShowDeleteAllWarning,
                            setFoodListSort = setFoodListSort
                        )

                    }
                )
            },
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = onClickFab
                ) {
                    Icon(
                        Icons.Outlined.Add,
                        contentDescription = stringResource(R.string.add_food_description)
                    )
                }
            },
            isFloatingActionButtonDocked = true
        ) {
            UiDisplayHandler(
                foodList = foodList,
                setFoodQuantity = setFoodQuantity,
                deleteFood = deleteFood,
                screenState = screenState
            )
            DeleteDialog(
                showDialog = showDeleteAllWarning,
                setShowDialog = setShowDeleteAllWarning,
                deleteAllFoods = deleteAllFoods,
                message = stringResource(
                    R.string.delete_warning
                )
            )
        }
    }
}

@Composable
private fun UiDisplayHandler(
    foodList: List<FoodUi>,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
    screenState: ResultOf<Unit>
) {
    return when (screenState) {
        is ResultOf.Success -> FoodListContent(
            modifier = Modifier.fillMaxSize(),
            foodList = foodList,
            setFoodQuantity = setFoodQuantity,
            deleteFood = deleteFood
        )
        is ResultOf.Error -> ErrorUi(
            showDialog = true,
            dismissDialog = {},
            message = stringResource(R.string.error_getting_list)
        )
        ResultOf.Loading -> LoadingUi(showDialog = true)
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun FoodListScreenPreview() {
    SqlDelightPrototypeTheme {
        FoodListScreen(
            foodList = listOf(),
            onClickFab = {},
            setFoodQuantity = {},
            deleteFood = {},
            screenState = ResultOf.Success(data = Unit),
            deleteAllFoods = {},
            setFoodListSort = {},
            navigateToAddUserScreen = {}
        )
    }
}