package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun ButtonBase(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonBasePreview() {
    SqlDelightPrototypeTheme {
        ButtonBase(
            onClick = {},
            content = {
                Text("Test")
            }
        )
    }
}
