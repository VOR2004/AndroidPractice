package ru.itis.androidpractice.features.topic.domain.usecases

import com.google.firebase.FirebaseException
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.domain.repositories.CommentRepository
import ru.itis.androidpractice.features.topic.domain.validation.CommentValidator
import java.util.UUID
import javax.inject.Inject

class AddCommentUseCase @Inject constructor(
    private val commentRepository: CommentRepository,
    private val commentValidator: CommentValidator
) {
    data class Input(
        val topicId: String,
        val authorId: String,
        val text: String,
        val authorName: String
    )
    data class ValidationResult(
        val commentError: String? = null,
        val isSuccess: Boolean = false,
        val id: String? = null
    )

    suspend fun execute(input: Input): ValidationResult {
        val commentError = commentValidator.getErrorMessage(input.text)

        if (commentError != null) {
            return ValidationResult(commentError)
        }
        val returnId = UUID.randomUUID().toString()
        val comment = CommentEntity(
            id = returnId,
            topicId = input.topicId,
            authorId = input.authorId,
            text = input.text,
            authorName = input.authorName
        )
        return try {
            commentRepository.addComment(comment)
            ValidationResult(isSuccess = true, id = returnId)
        } catch (_: FirebaseException) {
            return ValidationResult(isSuccess = false)
        }
    }
}