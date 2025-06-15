package ru.itis.androidpractice.features.topic.data.remote.entities

import androidx.compose.runtime.Immutable

@Immutable
data class CommentLikeEntity(
    val commentId: String = "",
    val userId: String = ""
)