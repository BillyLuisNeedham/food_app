package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.presentation.models.FoodUi
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun FoodListItem(
    modifier: Modifier = Modifier,
    food: FoodUi,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        FoodListItemContent(
            modifier = Modifier.padding(vertical = 8.dp),
            food = food,
            setFoodQuantity = setFoodQuantity,
            deleteFood = deleteFood
        )
    }
}

@Composable
private fun FoodListItemContent(
    modifier: Modifier = Modifier,
    food: FoodUi,
    setFoodQuantity: (food: FoodUi) -> Unit,
    deleteFood: (food: FoodUi) -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .weight(3f)
                .padding(horizontal = 16.dp),
            text = food.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis

        )
        ExpiresDisplay(
            modifier = Modifier.weight(2f),
            expiresMessage = food.expirationMessage
        )
        QuantityDisplay(
            modifier = Modifier.weight(2f),
            quantity = food.quantity,
            setQuantity = {
                val newFood = food.copy(quantity = it)
                setFoodQuantity(newFood)
            }
        )
        DeleteFood(
            modifier = Modifier.weight(1f),
            food = food, deleteFood = deleteFood
        )
    }
}

@Composable
private fun DeleteFood(
    modifier: Modifier = Modifier,
    food: FoodUi,
    deleteFood: (food: FoodUi) -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = { deleteFood(food) }
    ) {
        Icon(
            Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_food_content_description)
        )
    }
}

@Composable
private fun ExpiresDisplay(
    modifier: Modifier = Modifier,
    expiresMessage: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.expires_in))
        Text(text = expiresMessage)
    }
}

@Composable
private fun QuantityDisplay(
    modifier: Modifier = Modifier,
    quantity: Int,
    setQuantity: (Int) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { setQuantity(quantity - 1) }
        ) {
            Icon(
                Icons.Filled.Remove,
                contentDescription = stringResource(R.string.reduce_amount_content_description)
            )
        }

        Text(text = quantity.toString())

        IconButton(
            onClick = { setQuantity(quantity + 1) }
        ) {

            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(R.string.increase_amount_content_description)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FoodListItemPreview() {
    val food = FoodUi(
        id = 1L,
        name = "Apples that are really delicious Apples that are really delicious Apples that are really delicious Apples that are really delicious Apples that are really delicious Apples that are really delicious ",
        quantity = 3,
        expirationMessage = "4 Days",
        expirationDate = 10L
    )

    SqlDelightPrototypeTheme {
        FoodListItem(
            food = food,
            setFoodQuantity = {},
            deleteFood = {}
        )
    }
}
