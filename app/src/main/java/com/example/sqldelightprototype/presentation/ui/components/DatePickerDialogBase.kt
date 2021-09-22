package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun DatePickerDialogBase(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    setDate: (year: Int, month: Int, day: Int) -> Unit,
) {
    if (showDialog) {
            Dialog(
                onDismissRequest = { setShowDialog(false) },
                properties = DialogProperties(),
            ) {
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .background(MaterialTheme.colors.surface)

                ) {
                DatePickerView(
                    setDate = setDate,
                    dismissCalendar = { setShowDialog(false) }
                )
            }
        }
    }
}