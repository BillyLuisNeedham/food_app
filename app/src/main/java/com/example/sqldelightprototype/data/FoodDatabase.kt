package com.example.sqldelightprototype.data

import android.app.Application
import android.content.Context
import com.example.sqldelightprototype.Database
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


class FoodDatabase(private val context: Context) {

    companion object {
        private const val DB_NAME = "food.db"

    }

    private val driver = AndroidSqliteDriver(Database.Schema, context, DB_NAME)
    val queries = Database(driver)
}