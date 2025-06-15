package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity
import ru.itis.androidpractice.features.topic.domain.repositories.ReplyRepository
import javax.inject.Inject

class GetRepliesForCommentUseCase @Inject constructor(
    private val replyRepository: ReplyRepository
) {
    suspend fun execute(id: String): Result<List<ReplyEntity>> {
        return replyRepository.getRepliesForComment(id)
    }
}