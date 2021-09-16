package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    label: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = onTextChange,
        label = {
            label?.let {
                Text(it)
            }
        },
        keyboardOptions = keyboardOptions
    )
}

@Preview
@Composable
fun TextInputPreview() {
    SqlDelightPrototypeTheme {
        TextInput(text = "Test", onTextChange = {}, label = "label")
    }
}