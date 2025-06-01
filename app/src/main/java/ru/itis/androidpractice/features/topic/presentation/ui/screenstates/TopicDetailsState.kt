package ru.itis.androidpractice.features.topic.presentation.ui.screenstates

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity

@Immutable
data class TopicDetailsState(
    val title: String = "",
    val description: String = "",
    val currentCommentText: TextFieldValue = TextFieldValue(""),
    val commentError: String? = null,
    val comments: ImmutableList<CommentEntity> = persistentListOf(),
    val replyToComment: CommentEntity? = null,
    val lastMention: String? = null,
    val isLoading: Boolean = false
)