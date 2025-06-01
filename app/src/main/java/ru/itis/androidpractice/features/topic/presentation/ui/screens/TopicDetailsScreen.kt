package ru.itis.androidpractice.features.topic.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.androidpractice.features.topic.presentation.ui.uiparts.CommentInputBar
import ru.itis.androidpractice.features.topic.presentation.ui.viewmodel.TopicDetailsViewModel
import ru.itis.androidpractice.features.topic.presentation.ui.uiparts.CommentsList
import ru.itis.androidpractice.features.topic.presentation.ui.uiparts.ReplyToCommentOverlay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicDetailsScreen(
    id: String,
    viewModel: TopicDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val stateRefresh = rememberPullToRefreshState()
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()

    LaunchedEffect(id) {
        viewModel.loadTopic(id)
    }
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            bottomBar = {
                CommentInputBar(
                    currentText = state.currentCommentText,
                    onTextChange = viewModel::onCommentTextChanged,
                    onSendClick = { viewModel.sendCommentOrReply(id) },
                    commentError = state.commentError,
                    scrollBehavior = scrollBehavior
                )
            },
            floatingActionButtonPosition = FabPosition.Start,
            floatingActionButton = {
                if (state.replyToComment != null) {
                    ReplyToCommentOverlay(
                        replyToComment = state.replyToComment!!,
                        scrollBehavior = scrollBehavior,
                        onClearReply = viewModel::clearReply,
                        modifier = Modifier
                    )
                }
            },
        ) { paddingValues ->
            PullToRefreshBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                isRefreshing = state.isLoading,
                state = stateRefresh,
                onRefresh = {
                    viewModel.refreshAndLoadTopic(id)
                },
            ) {
                CommentsList(
                    comments = state.comments,
                    onReplyClick = viewModel::onReplyClick,
                    lazyListState = lazyListState,
                    title = state.title,
                    description = state.description
                )
            }
        }
}


