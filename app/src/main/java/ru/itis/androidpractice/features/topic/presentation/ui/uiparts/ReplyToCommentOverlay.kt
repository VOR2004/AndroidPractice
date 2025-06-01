package ru.itis.androidpractice.features.topic.presentation.ui.uiparts

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyToCommentOverlay(
    replyToComment: CommentEntity,
    scrollBehavior: BottomAppBarScrollBehavior?,
    onClearReply: () -> Unit,
    modifier: Modifier = Modifier
) {
    val offset = scrollBehavior?.state?.heightOffset ?: 0f
    val alpha = 1f + (offset / 80f)

    Surface(
        color = MaterialTheme.colorScheme.primary.copy(alpha = 1f),
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .animateContentSize()
            .graphicsLayer { this.alpha = alpha.coerceIn(0f, 1f) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = "@${replyToComment.authorId}",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onClearReply,
                modifier = Modifier.size(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Отменить ответ",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
