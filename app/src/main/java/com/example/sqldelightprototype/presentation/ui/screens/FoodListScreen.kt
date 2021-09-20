package com.example.sqldelightprototype.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.presentation.models.FoodUi
import com.example.sqldelightprototype.presentation.ui.components.FoodListItem
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun FoodListScreen(
    foodList: ResultOf<List<FoodUi>>,
    onClickFab: () -> Unit,
    setFoodQuantity: (Int) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    Scaffold(
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
            deleteFood = deleteFood
        )
    }
}

@Composable
private fun UiDisplayHandler(
    foodList: ResultOf<List<FoodUi>>,
    setFoodQuantity: (Int) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    return when (foodList) {
        is ResultOf.Success -> FoodListContent(
            modifier = Modifier.fillMaxSize(),
            foodList = foodList.data,
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
    setFoodQuantity: (Int) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(foodList) { food ->
            FoodListItem(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
                ,
                food = food,
                setQuantity = setFoodQuantity,
                deleteFood = deleteFood
            )
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
            foodList = ResultOf.Error(),
            onClickFab = {},
            setFoodQuantity = {},
            deleteFood = {}
        )
    }
}