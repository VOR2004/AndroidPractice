package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity
import ru.itis.androidpractice.features.topic.domain.repositories.ReplyRepository
import java.util.UUID
import javax.inject.Inject

class AddReplyUseCase @Inject constructor(
    private val replyRepository: ReplyRepository
) {
    data class Input(
        val commentId: String,
        val authorId: String,
        val authorName: String,
        val text: String,
        val replyToAuthorName: String
    )
    data class ValidationResult(
        val replyError: String? = null,
        val isSuccess: Boolean = false,
        val id: String? = null
    )

    suspend fun execute(input: Input): ValidationResult {
        if (input.text.isBlank()) {
            return ValidationResult(replyError = "Ответ не может быть пустым")
        }
        val id = UUID.randomUUID().toString()
        val reply = ReplyEntity(
            id = id,
            commentId = input.commentId,
            authorId = input.authorId,
            authorName = input.authorName,
            text = input.text,
            replyToAuthorName = input.replyToAuthorName
        )
        return try {
            replyRepository.addReply(reply)
            ValidationResult(isSuccess = true, id = id)
        } catch (_: Exception) {
            ValidationResult(isSuccess = false)
        }
    }
}
