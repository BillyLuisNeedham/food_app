package com.example.sqldelightprototype.presentation.ui.screens.adduserscreen

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.presentation.ui.components.NavigateBackButton

@Composable
fun AddUserScreen(
    navigateBack: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background,
                title = { Text(stringResource(R.string.add_user)) },
                navigationIcon = {
                    NavigateBackButton(onClick = navigateBack)
                }
            )
        }
    ) {

    }
}