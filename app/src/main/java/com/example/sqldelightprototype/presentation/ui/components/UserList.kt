package com.example.sqldelightprototype.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sqldelightprototype.R
import com.example.sqldelightprototype.domain.models.User
import com.example.sqldelightprototype.presentation.ui.theme.SqlDelightPrototypeTheme

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    userList: List<User>,
    onClickAddUser: () -> Unit,
    onClickUser: (User) -> Unit,
) {
    Column(
        modifier = modifier
            .defaultMinSize(minHeight = 200.dp)
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(R.string.users),
                style = MaterialTheme.typography.h5
            )

            IconButton(
                onClick = onClickAddUser
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(
                        R.string.navigate_to_add_user_content_description
                    )
                )
            }
        }

        LazyColumn {
            items(items = userList) { user ->
                ClickableText(
                    modifier = Modifier.padding(16.dp),
                    text = AnnotatedString(user.name),
                    onClick = {onClickUser(user)}
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    SqlDelightPrototypeTheme {
        UserList(
            userList = listOf(
                User(name = "Hello World"),
                User(name = "Hello World"),
                User(name = "Hello World"),
                User(name = "Hello World"),
            ),
            onClickAddUser = {},
            onClickUser = {}
        )
    }
}