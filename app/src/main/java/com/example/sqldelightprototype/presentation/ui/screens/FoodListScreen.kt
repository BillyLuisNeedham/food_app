package com.example.sqldelightprototype.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.presentation.models.FoodUi
import com.example.sqldelightprototype.presentation.ui.components.ButtonBase
import com.example.sqldelightprototype.presentation.ui.components.FoodListItem
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun FoodListScreen(
    foodList: List<FoodUi>,
    onClickFab: () -> Unit,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
    screenState: ResultOf<Unit>,
    deleteAllFoods: () -> Unit
) {
    val (showMenu, setShowMenu) = remember { mutableStateOf(false) }
    val (showDeleteAllWarning, setShowDeleteAllWarning) =
        remember { mutableStateOf(false) }

    Scaffold(
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
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickFab
            ) {
                Icon(
                    Icons.Outlined.Add,
                    contentDescription = stringResource(R.string.add_food_description)
                )
            }
        }
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
            deleteAllFoods = deleteAllFoods
        )
    }
}

@Composable
fun DeleteDialog(
    modifier: Modifier = Modifier,
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    deleteAllFoods: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { setShowDialog(false) },
            title = { Text(stringResource(R.string.warning)) },
            confirmButton = {
                ButtonBase(onClick = {
                    setShowDialog(false)
                    deleteAllFoods()
                }) {
                    Text(stringResource(R.string.continue_dialog))
                }
            },
            dismissButton = {
                ButtonBase(onClick = {
                    setShowDialog(false)
                }) {
                    Text(stringResource(R.string.cancel))
                }
            },
            text = {
                Text(stringResource(R.string.delete_warning))
            }
        )
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
        is ResultOf.Error -> ErrorUi()
        ResultOf.Loading -> LoadingUi()
    }
}

@Composable
private fun FoodListContent(
    modifier: Modifier = Modifier,
    foodList: List<FoodUi>,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = listState
    ) {
        item {
            Spacer(modifier = Modifier.padding(top = 16.dp))
        }
        items(foodList) { food ->
            FoodListItem(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(
                        bottom = 16.dp

                    ),
                food = food,
                setFoodQuantity = setFoodQuantity,
                deleteFood = deleteFood
            )
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }

}

@Composable
private fun LoadingUi() {
    CenteredContent {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorUi() {
    CenteredContent {
        Text(text = stringResource(R.string.error_getting_list))
    }
}

@Composable
private fun CenteredContent(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}

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
            deleteAllFoods = {}
        )
    }
}