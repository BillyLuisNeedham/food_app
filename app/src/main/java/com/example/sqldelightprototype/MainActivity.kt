package com.example.sqldelightprototype

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqldelightprototype.ui.theme.SqlDelightPrototypeTheme
import com.squareup.sqldelight.android.AndroidSqliteDriver

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val androidSqlDriver = AndroidSqliteDriver(
//            schema =
//        )

        setContent {
            SqlDelightPrototypeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Big cheese")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SqlDelightPrototypeTheme {
        Greeting("Android")
    }
}