package com.billyluisneedham.foodapp

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FoodDatabaseTest {

    private val inMemorySqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
        Database.Schema.create(this)
    }

    private val queries = Database(inMemorySqlDriver).foodQueries

    @Test
    fun smokeTest() {
        val emptyItems = queries.selectAllSortByName().executeAsList()
        assertThat(emptyItems.size, `is`(0))

        queries.insertOrReplace(
            id = null,
            name = "test",
            quantity = 1,
            expiry_date = 10L
        )

        val items = queries.selectAllSortByName().executeAsList()
        assertThat(items.size, `is`(1))

        val testItem = queries.selectByName("test").executeAsOneOrNull()
        assertThat(testItem?.quantity?.toInt(), `is`(1))
    }
}
