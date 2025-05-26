package ru.itis.androidpractice.features.topic.domain.repositories

import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity

interface CommentRepository {
    suspend fun addComment(comment: CommentEntity): Result<Unit>
    suspend fun getCommentsForTopic(topicId: String): Result<List<CommentEntity>>
}