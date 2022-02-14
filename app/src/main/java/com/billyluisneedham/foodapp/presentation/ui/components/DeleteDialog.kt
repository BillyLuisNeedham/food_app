package com.billyluisneedham.foodapp.presentation.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.billyluisneedham.foodapp.R

@Composable
fun DeleteDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    onConfirmClicked: () -> Unit,
    message: String
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { setShowDialog(false) },
            title = { Text(stringResource(R.string.warning)) },
            confirmButton = {
                ButtonBase(onClick = {
                    setShowDialog(false)
                    onConfirmClicked()
                }) {
                    Text(stringResource(R.string.continue_dialog))
                }
            },
            dismissButton = {
                ButtonBase(onClick = {
                    setShowDialog(false)
                }) {
                    Text(stringResource(R.string.cancel))
                }
            },
            text = {
                Text(message)
            }
        )
    }
}
