package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.domain.repositories.CommentRepository
import javax.inject.Inject

class GetCommentsByTopicIdUseCase @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun execute(id: String): Result<List<CommentEntity>> {
        return commentRepository.getCommentsForTopic(id)
    }
}