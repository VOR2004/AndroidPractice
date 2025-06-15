package ru.itis.androidpractice.features.topic.domain.repositories

interface CommentLikeRepository {
    suspend fun toggleLike(commentId: String, userId: String): Result<Boolean>
}