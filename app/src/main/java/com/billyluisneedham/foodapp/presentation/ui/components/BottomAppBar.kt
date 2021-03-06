package com.billyluisneedham.foodapp.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.billyluisneedham.foodapp.R

@Composable
fun BottomAppBarBase(
    onClickPerson: () -> Unit
) {
    BottomAppBar(
        elevation = 10.dp,
        cutoutShape = RoundedCornerShape(50.dp)
    ) {
        IconButton(
            onClick = onClickPerson
        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = stringResource(R.string.open_menu_content_description)
            )
        }

        Spacer(Modifier.weight(1f, true))
    }
}
