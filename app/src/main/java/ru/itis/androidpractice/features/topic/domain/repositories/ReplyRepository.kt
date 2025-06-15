package ru.itis.androidpractice.features.topic.domain.repositories

import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity

interface ReplyRepository {
    suspend fun addReply(reply: ReplyEntity): Result<Unit>
    suspend fun getRepliesForComment(commentId: String): Result<List<ReplyEntity>>
}