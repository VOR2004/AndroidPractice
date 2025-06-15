package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.features.topic.domain.repositories.CommentLikeRepository
import javax.inject.Inject

class AddLikeUseCase @Inject constructor(
    private val commentLikeRepository: CommentLikeRepository
) {
    suspend fun execute(commentId: String, userId: String): Result<Boolean>  {
        return commentLikeRepository.toggleLike(commentId = commentId, userId = userId)
    }
}