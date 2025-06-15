package ru.itis.androidpractice.features.topic.data.remote.entities

import androidx.compose.runtime.Immutable
import java.util.Date

@Immutable
data class CommentEntity(
    val id: String = "",
    val topicId: String = "",
    val authorId: String = "",
    val text: String = "",
    val createdAt: Date = Date(),
    val isDeleted: Boolean = false,
    val rating: Int = 0,
    val replies: List<ReplyEntity> = emptyList(),
    val authorName: String = ""
)