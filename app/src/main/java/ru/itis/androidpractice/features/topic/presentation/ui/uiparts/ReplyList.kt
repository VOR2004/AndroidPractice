package ru.itis.androidpractice.features.topic.presentation.ui.uiparts

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity

@Composable
fun ReplyList(
    onToggleReplies: () -> Unit,
    isExpanded: Boolean,
    replies: List<ReplyEntity>,
    onReplyClick: () -> Unit
) {

    Row {
        IconButton(
            onClick = onReplyClick,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Reply,
                contentDescription = "Ответить"
            )
        }
        TextButton(
            onClick = onToggleReplies,
        ) {
            Text(if (isExpanded) "Скрыть ответы" else "Показать ответы")
        }
    }

    if (isExpanded) {
        replies.forEach { reply ->
            ReplyItem(reply = reply)
        }
    }
}