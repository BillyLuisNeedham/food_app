package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.presentation.models.FoodUi
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun FoodListItem(
    modifier: Modifier = Modifier,
    food: FoodUi,
    setQuantity: (Int) -> Unit,
    deleteFood: (foodId: Long) -> Unit

) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = food.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5

        )
        ExpiresDisplay(expiresMessage = food.expirationMessage)
        QuantityDisplay(
            quantity = food.quantity,
            setQuantity = setQuantity
        )
        DeleteFood(food = food, deleteFood = deleteFood)
    }
}

@Composable
private fun DeleteFood(
    modifier: Modifier = Modifier,
    food: FoodUi,
    deleteFood: (foodId: Long) -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = { deleteFood(food.id) }
    ) {
        Icon(Icons.Filled.Delete, contentDescription = stringResource(R.string.delete_food_content_description))
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
        name = "Apples",
        quantity = 3,
        expirationMessage = "4 Days"
    )

    SqlDelightPrototypeTheme {
        FoodListItem(
            food = food,
            setQuantity = {},
            deleteFood = {}
        )
    }
}