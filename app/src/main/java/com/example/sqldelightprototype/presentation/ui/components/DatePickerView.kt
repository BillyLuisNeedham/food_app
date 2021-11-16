package com.example.sqldelightprototype.presentation.ui.components

import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DatePickerView(
    modifier: Modifier = Modifier,
    setDate: (year: Int, month: Int, day: Int) -> Unit,
    dismissCalendar: () -> Unit
) {

    AndroidView(
        modifier = modifier.wrapContentWidth(),
        factory = { CalendarView(it) },
        update = { views ->
            views.setOnDateChangeListener { _, year, month, day ->
                setDate(year, month, day)
                dismissCalendar()
            }
        }

    )
}
