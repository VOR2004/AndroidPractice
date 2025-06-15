package ru.itis.androidpractice.features.topic.presentation.ui.uiparts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.R
import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity

@Composable
fun ReplyList(
    onToggleReplies: () -> Unit,
    isExpanded: Boolean,
    replies: List<ReplyEntity>,
    onReplyClick: () -> Unit
) {

    Row(modifier = Modifier.padding(start = 16.dp)) {
        IconButton(
            onClick = onReplyClick,
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Reply,
                contentDescription = stringResource(R.string.reply)
            )
        }
        TextButton(
            onClick = onToggleReplies,
        ) {
            Text(if (isExpanded) stringResource(R.string.dismiss_replies)
            else stringResource(R.string.reply)
            )
        }
    }

    if (isExpanded) {
        replies.forEach { reply ->
            ReplyItem(reply = reply)
        }
    }
}