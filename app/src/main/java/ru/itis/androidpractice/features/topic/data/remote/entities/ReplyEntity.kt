package ru.itis.androidpractice.features.topic.data.remote.entities

import java.util.Date

data class ReplyEntity(
    val id: String = "",
    val commentId: String = "",
    val authorId: String = "",
    val authorName: String = "",
    val text: String = "",
    val createdAt: Date = Date(),
    val isDeleted: Boolean = false,
    val replyToAuthorName: String = ""   // Имя автора комментария, на который отвечают
)
