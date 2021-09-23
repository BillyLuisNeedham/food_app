package com.example.sqldelightprototype.presentation.ui.screens.adduserscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.domain.ResultOf
import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.presentation.models.ErrorState
import com.example.sqldelightprototype.presentation.ui.components.NavigateBackButton
import com.example.sqldelightprototype.presentation.ui.components.TextInput
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun AddUserScreen(
    screenState: ResultOf<Unit>,
    navigateBack: () -> Unit,
    addUser: (User) -> Unit
) {
    val (name, setName) = remember { mutableStateOf("") }
    val (nameError, setNameError) = remember {
        mutableStateOf(
            ErrorState(
                error = false,
                messageResource = R.string.error_blank_name
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background,
                title = { Text(stringResource(R.string.add_user)) },
                navigationIcon = {
                    NavigateBackButton(onClick = navigateBack)
                },
                actions = {
                    IconButton(onClick = {
                        when {
                            name.isBlank() -> setNameError(
                                nameError.copy(error = true)
                            )
                            else -> addUser(
                                User(name = name)
                            )
                        }
                    }
                    ) {
                        Icon(
                            Icons.Filled.Done,
                            contentDescription = stringResource(R.string.add_user_content_description)
                        )
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            TextInput(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                onTextChange = setName,
                label = stringResource(R.string.name),
                errorState = nameError,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddUserScreenPreview() {
    SqlDelightPrototypeTheme {
        AddUserScreen(
            screenState = ResultOf.Success(Unit),
            navigateBack = {},
            addUser = {}
        )
    }
}