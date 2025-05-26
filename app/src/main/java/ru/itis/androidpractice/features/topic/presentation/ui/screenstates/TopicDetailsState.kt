package ru.itis.androidpractice.features.topic.presentation.ui.screenstates

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity

data class TopicDetailsState(
    val title: String = "",
    val description: String = "",
    val currentCommentText: String = "",
    val commentError: String? = null,
    val comments: ImmutableList<CommentEntity> = persistentListOf(),
)