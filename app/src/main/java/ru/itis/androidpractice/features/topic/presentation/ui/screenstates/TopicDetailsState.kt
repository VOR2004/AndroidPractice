package ru.itis.androidpractice.features.topic.presentation.ui.screenstates

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.text.input.TextFieldValue
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity

@Immutable
data class TopicDetailsState(
    val title: String = "",
    val description: String = "",
    val currentCommentText: TextFieldValue = TextFieldValue(""),
    val commentError: String? = null,
    val comments: SnapshotStateList<CommentEntity> = mutableStateListOf(),
    val replyToComment: CommentEntity? = null,
    val lastMention: String? = null,
    val isLoading: Boolean = false,
    val visibleReplies: SnapshotStateMap<String, SnapshotStateList<ReplyEntity>> = mutableStateMapOf(),
    val expandedComments: SnapshotStateMap<String, Boolean> = mutableStateMapOf(),
)