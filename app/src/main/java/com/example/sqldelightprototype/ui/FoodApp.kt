package com.example.sqldelightprototype.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqldelightprototype.Food
import com.example.sqldelightprototype.ui.theme.SqlDelightPrototypeTheme

@Composable
//TODO remove db and implement properly using MVVM and DI
fun FoodApp(
    addFood: () -> Unit,
    listOfFood: List<Food>
) {
    SqlDelightPrototypeTheme {
        Scaffold(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Click to add a fruit")
                Row {
                    Button(
                        onClick = { addFood() },
                        content = {
                            Text("Add")
                        }
                    )
                }
                listOfFood.map {
                    Text(
                        "id: ${it.id}\n" +
                                "name: ${it.name}\n" +
                                "quantity: ${it.quantity}"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodAppPreview() {
    FoodApp(
        addFood = {},
        listOfFood = listOf(Food(id = 1, name = "YUMMY", quantity = 1))
    )
}