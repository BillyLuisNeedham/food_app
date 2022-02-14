package com.billyluisneedham.foodapp.presentation.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.billyluisneedham.foodapp.R

@Composable
fun NavigateBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_navigation_content_description)
        )
    }
}
