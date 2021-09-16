package com.example.sqldelightprototype.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.sqldelightprototype.FoodData
import com.example.sqldelightprototype.data.FoodDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //TODO REMOVE when done testing
    private var count = 0L
    private var foods: List<FoodData>? = null

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

    private fun getFoods(database: FoodDatabase): List<FoodData> {
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
