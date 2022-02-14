package com.billyluisneedham.foodapp.presentation.ui.screens.foodlistscreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.billyluisneedham.foodapp.presentation.models.FoodUi
import com.billyluisneedham.foodapp.presentation.ui.components.FoodListItem

@Composable
fun FoodListContent(
    modifier: Modifier = Modifier,
    foodList: List<FoodUi>,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 8.dp),
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
