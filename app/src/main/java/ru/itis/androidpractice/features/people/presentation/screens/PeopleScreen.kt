package ru.itis.androidpractice.features.people.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.itis.androidpractice.core.navigation.Routes
import ru.itis.androidpractice.core.ui.uiparts.BottomNavigationBar
import ru.itis.androidpractice.features.people.presentation.viewmodel.PeopleViewModel
import ru.itis.androidpractice.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PeopleScreen(
    onNavigateTo: (Routes) -> Unit,
    currentRoute: Routes,
    peopleViewModel: PeopleViewModel = hiltViewModel()
) {
    val state by peopleViewModel.viewStates.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onItemClick = onNavigateTo,
                currentRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.inputUsername,
                    onValueChange = { peopleViewModel.onInputUsernameChange(it) },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(stringResource(R.string.enter_nickname)) },
                    isError = state.isError,
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { peopleViewModel.searchUser() }) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = stringResource(R.string.search)
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = { peopleViewModel.searchUser() }
                    )
                )
                if (state.isError) {
                    Text(
                        text = stringResource(R.string.user_not_found),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))

                if (state.username.isNotEmpty() && !state.isError) {
                    Spacer(modifier = Modifier.height(32.dp))
                    GlideImage(
                        model = state.avatarUrl ?: R.drawable.avatar_ph,
                        contentDescription = stringResource(R.string.user_avatar),
                        modifier = Modifier
                            .size(128.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = state.username,
                        fontSize = 32.sp,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(R.string.user_rating),
                        fontSize = 24.sp,
                    )
                    Text(
                        modifier = Modifier.padding(top = 16.dp),
                        text = state.rating,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = stringResource(R.string.user_status),
                        fontSize = 24.sp,
                    )
                    Text(
                        modifier = Modifier.padding(top = 48.dp),
                        text = state.status,
                        fontSize = 48.sp,
                        fontFamily = FontFamily.Monospace
                    )
                } else if (state.username.isEmpty() && !state.isError) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.enter_nickname_hint),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}
