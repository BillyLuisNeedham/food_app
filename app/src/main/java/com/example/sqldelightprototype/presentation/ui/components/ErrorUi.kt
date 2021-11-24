package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun ErrorUi(
    showDialog: Boolean,
    dismissDialog: () -> Unit,
    message: String
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = dismissDialog,
            properties = DialogProperties()
        ) {
            Surface(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.error),
                        style = MaterialTheme.typography.h6
                    )
                    Text(message)
                    ButtonBase(onClick = dismissDialog) {
                        Text(stringResource(R.string.continue_dialog))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorUiPreview() {
    SqlDelightPrototypeTheme {
        ErrorUi(
            showDialog = true,
            dismissDialog = {},
            message = "There has been an error, please try again"
        )
    }
}
