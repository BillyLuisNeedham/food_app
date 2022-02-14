package com.billyluisneedham.foodapp.presentation.ui.screens.foodlistscreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
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
import com.billyluisneedham.foodapp.R
import com.billyluisneedham.foodapp.domain.ResultOf
import com.billyluisneedham.foodapp.domain.models.User
import com.billyluisneedham.foodapp.presentation.models.FoodUi
import com.billyluisneedham.foodapp.presentation.ui.components.BottomAppBarBase
import com.billyluisneedham.foodapp.presentation.ui.components.DeleteDialog
import com.billyluisneedham.foodapp.presentation.ui.components.DropdownMenuFoodList
import com.billyluisneedham.foodapp.presentation.ui.components.ErrorUi
import com.billyluisneedham.foodapp.presentation.ui.components.LoadingUi
import com.billyluisneedham.foodapp.presentation.ui.components.UserList
import com.billyluisneedham.foodapp.presentation.ui.theme.SqlDelightPrototypeTheme
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun FoodListScreen(
    foodList: List<FoodUi>,
    onClickFab: () -> Unit,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
    screenState: com.billyluisneedham.foodapp.domain.ResultOf<Unit>,
    deleteAllFoods: () -> Unit,
    setFoodListSort: (FoodListScreenViewModel.Companion.SortFoods) -> Unit,
    navigateToAddUserScreen: () -> Unit,
    userList: List<com.billyluisneedham.foodapp.domain.models.User>,
    selectedUsers: List<com.billyluisneedham.foodapp.domain.models.User>,
    onLongPressUserListItem: (com.billyluisneedham.foodapp.domain.models.User) -> Unit,
    clearSelectedUserIds: () -> Unit,
    deleteAllSelectedUsers: () -> Unit,
) {
    val (showMenu, setShowMenu) = remember { mutableStateOf(false) }
    val (showDeleteAllFoodWarning, setShowDeleteAllFoodWarning) =
        remember { mutableStateOf(false) }
    val (showDeleteAllUsersWarning, setShowDeleteAllUsersWarning) =
        remember { mutableStateOf(false) }
    val userDialogState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coScope = rememberCoroutineScope()

    if (!userDialogState.isVisible) {
        clearSelectedUserIds()
    }

    ModalBottomSheetLayout(
        sheetState = userDialogState,
        sheetContent = {
            UserList(
                userList = userList,
                onClickAddUser = navigateToAddUserScreen,
                onClickUser = {
                    // TODO write
                    Log.d("FoodListScreen", "clicked $it.name")
                },
                onLongPressUser = onLongPressUserListItem,
                selectedUsers = selectedUsers,
                onClickDeleteUsers = { setShowDeleteAllUsersWarning(true) }
            )
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
                            setShowDeleteAllWarning = setShowDeleteAllFoodWarning,
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
                showDialog = showDeleteAllFoodWarning,
                setShowDialog = setShowDeleteAllFoodWarning,
                onConfirmClicked = deleteAllFoods,
                message = stringResource(
                    R.string.delete_food_warning
                )
            )
            DeleteDialog(
                showDialog = showDeleteAllUsersWarning,
                setShowDialog = setShowDeleteAllUsersWarning,
                onConfirmClicked = deleteAllSelectedUsers,
                message = stringResource(
                    R.string.delete_users_warning
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
    screenState: com.billyluisneedham.foodapp.domain.ResultOf<Unit>
) {
    return when (screenState) {
        is com.billyluisneedham.foodapp.domain.ResultOf.Success -> FoodListContent(
            modifier = Modifier.fillMaxSize(),
            foodList = foodList,
            setFoodQuantity = setFoodQuantity,
            deleteFood = deleteFood
        )
        is com.billyluisneedham.foodapp.domain.ResultOf.Error -> ErrorUi(
            showDialog = true,
            dismissDialog = {},
            message = stringResource(R.string.error_getting_list)
        )
        com.billyluisneedham.foodapp.domain.ResultOf.Loading -> LoadingUi(showDialog = true)
    }
}

@ExperimentalFoundationApi
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
            screenState = com.billyluisneedham.foodapp.domain.ResultOf.Success(data = Unit),
            deleteAllFoods = {},
            setFoodListSort = {},
            navigateToAddUserScreen = {},
            userList = listOf(),
            selectedUsers = listOf(),
            onLongPressUserListItem = {},
            clearSelectedUserIds = {},
            deleteAllSelectedUsers = {}
        )
    }
}
