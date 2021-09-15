package com.example.sqldelightprototype.data

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver


class database() {

    companion object {
        private const val DB_NAME = "test.db"
        private const val DB_SCHEMA = 1
    }
//    val driver: SqlDriver = AndroidSqliteDriver(Database.Schema.create(this), applicationContext, DB_NAME)
}