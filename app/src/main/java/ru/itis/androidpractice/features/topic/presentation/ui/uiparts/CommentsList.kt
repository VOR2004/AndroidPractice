package ru.itis.androidpractice.features.topic.presentation.ui.uiparts

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity

@Composable
fun CommentsList(
    comments: ImmutableList<CommentEntity>,
    onReplyClick: (CommentEntity) -> Unit,
    lazyListState: LazyListState,
    title: String,
    description: String
) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.fillMaxSize()
    ) {
        item{
            TopicHeader(title = title, description = description)
        }
        items(comments, key = { it.id }) { comment ->
            CommentItem(
                text = comment.text,
                authorName = comment.authorId,
                onReplyClick = { onReplyClick(comment) }
            )
        }
        item {
            Spacer(modifier = Modifier.height(64.dp))
        }
    }
}