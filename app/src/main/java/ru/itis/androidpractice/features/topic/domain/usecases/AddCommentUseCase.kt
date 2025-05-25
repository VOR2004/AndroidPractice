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
    data class Input(val topicId: String, val authorId: String, val text: String)
    data class ValidationResult(
        val commentError: String? = null,
        val isSuccess: Boolean = false
    )

    suspend fun execute(input: Input): ValidationResult {
        val commentError = commentValidator.getErrorMessage(input.text)

        if (commentError != null) {
            return ValidationResult(commentError)
        }

        val comment = CommentEntity(
            id = UUID.randomUUID().toString(),
            topicId = input.topicId,
            authorId = input.authorId,
            text = input.text,
        )
        return try {
            commentRepository.addComment(comment)
            ValidationResult(isSuccess = true)
        } catch (_: FirebaseException) {
            return ValidationResult(isSuccess = false)
        }
    }
}