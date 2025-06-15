package ru.itis.androidpractice.features.main.presentation.ui.uiparts

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun SearchTopBar(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    labelText: String,
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 64.dp, end = 8.dp),
            label = { Text(text = labelText) },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onSearchClick) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClick() })
        )
    }
}

