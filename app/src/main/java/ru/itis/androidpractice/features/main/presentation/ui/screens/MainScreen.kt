package ru.itis.androidpractice.features.main.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.androidpractice.core.navigation.Routes
import ru.itis.androidpractice.core.ui.uiparts.BottomNavigationBar
import ru.itis.androidpractice.features.main.presentation.ui.uiparts.SearchTopBar
import ru.itis.androidpractice.features.main.presentation.ui.uiparts.TopicsPager
import ru.itis.androidpractice.features.main.presentation.ui.viewmodel.MainViewModel
import ru.itis.androidpractice.R

@Composable
fun MainScreen(
    onNavigateTo: (Routes) -> Unit,
    currentRoute: Routes,
    onCreateClick: () -> Unit,
    onTopicClick: (String) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val state by mainViewModel.viewStates.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            SearchTopBar(
                searchQuery = state.searchQuery,
                onQueryChange = { mainViewModel.onSearchQueryChange(it) },
                onSearchClick = { mainViewModel.onSearchClick() },
                labelText = stringResource(R.string.search_topics),
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onItemClick = onNavigateTo,
                currentRoute = currentRoute
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreateClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_topic)
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when {
                state.errorMessage != null -> Text(
                    text = state.errorMessage!!,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
                else -> {
                    TopicsPager(
                        topics = state.topics,
                        onTopicClick = { onTopicClick(it.id) }
                    )
                }
            }
        }
    }
}