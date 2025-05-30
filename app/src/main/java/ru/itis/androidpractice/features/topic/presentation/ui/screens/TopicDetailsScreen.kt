package ru.itis.androidpractice.features.topic.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.androidpractice.features.topic.presentation.ui.viewmodel.TopicDetailsViewModel
import ru.itis.androidpractice.features.topic.presentation.ui.uiparts.CommentItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicDetailsScreen(
    id: String,
    viewModel: TopicDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()

    val canScrollUp by remember {
        derivedStateOf {
            lazyListState.canScrollBackward
        }
    }

    val scrollBehavior = if (canScrollUp) {
        BottomAppBarDefaults.exitAlwaysScrollBehavior()
    } else {
        null
    }

    LaunchedEffect(id) {
        viewModel.loadTopic(id)
    }

    Scaffold(
        modifier = Modifier
            .then(
                if (scrollBehavior != null)
                    Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                else
                    Modifier
            ),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                scrollBehavior = scrollBehavior,
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 4.dp,
                windowInsets = WindowInsets(0.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        isError = state.commentError != null,
                        value = state.currentCommentText,
                        onValueChange = { viewModel.onCommentTextChanged(it) },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        placeholder = { Text("Оставьте комментарий...") },
                        maxLines = 5,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                        )
                    )
                    IconButton(
                        onClick = { viewModel.addComment(topicId = id) },
                        enabled = state.currentCommentText.isNotBlank()
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Отправить",
                            tint = if (state.currentCommentText.isNotBlank()) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                Color.Gray
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                Text(
                    text = state.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                Text(
                    text = state.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            items(state.comments) { comment ->
                CommentItem(
                    text = comment.text,
                    authorName = comment.authorId
                )
            }

            if (canScrollUp) {
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}