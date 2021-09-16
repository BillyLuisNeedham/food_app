package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    label: String? = null,
    text: String,
    onTextChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChange,
        label = {
            label?.let {
                Text(it)
            }
        }
    )
}

@Preview
@Composable
fun TextInputPreview() {
    SqlDelightPrototypeTheme {
        TextInput(text = "Test", onTextChange = {}, label = "label")
    }
}