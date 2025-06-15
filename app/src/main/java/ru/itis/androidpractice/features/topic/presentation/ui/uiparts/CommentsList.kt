package ru.itis.androidpractice.features.topic.presentation.ui.uiparts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity

@Composable
fun CommentsList(
    comments: SnapshotStateList<CommentEntity>,
    onReplyClick: (CommentEntity) -> Unit,
    lazyListState: LazyListState,
    title: String,
    description: String,
    expandedComments: SnapshotStateMap<String, Boolean>,
    visibleReplies: SnapshotStateMap<String, SnapshotStateList<ReplyEntity>>,
    onToggleReplies: (String) -> Unit,
    onLikeClick: suspend (commentId: String) -> Boolean,
) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            TopicHeader(
                title = title, description = description,
            )
        }
        items(comments, key = { it.id }) { comment ->
            CommentItem(
                text = comment.text,
                authorName = comment.authorName,
                onItemClick = { onLikeClick(comment.id) }
            )

            ReplyList(
                onToggleReplies = { onToggleReplies(comment.id) },
                isExpanded = expandedComments[comment.id] ?: false,
                replies = visibleReplies[comment.id] ?: emptyList(),
                onReplyClick = { onReplyClick(comment) },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}