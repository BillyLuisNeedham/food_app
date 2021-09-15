package com.example.sqldelightprototype.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.sqldelightprototype.Food
import com.example.sqldelightprototype.data.FoodDatabase
import com.example.sqldelightprototype.ui.theme.SqlDelightPrototypeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //TODO REMOVE when done testing
    private var count = 0L
    private var foods: List<Food>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO Refactor into proper MVVM with DI when done testing
        val database = FoodDatabase(context = this)
        foods = getFoods(database)

        setContent {
            FoodApp(
                listOfFood = foods ?: listOf(),
                addFood = { addFood(database) }
            )
        }
    }

    private fun getFoods(database: FoodDatabase): List<Food>? {
        return database.queries.foodQueries.selectAll().executeAsList()
    }

    private fun addFood(database: FoodDatabase) {
        lifecycleScope.launch {
            count++
            database.queries.foodQueries.insertOrReplace(
                id = null,
                name = "FOOOOD",
                quantity = count
            )
            foods = getFoods(database)
        }
    }
}
