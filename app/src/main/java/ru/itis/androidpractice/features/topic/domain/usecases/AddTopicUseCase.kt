package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity
import ru.itis.androidpractice.features.topic.data.remote.repositories.TopicRepository
import ru.itis.androidpractice.features.topic.domain.validation.DescriptionValidator
import ru.itis.androidpractice.features.topic.domain.validation.TitleValidator
import javax.inject.Inject

class AddTopicUseCase @Inject constructor(
    private val topicRepository: TopicRepository,
    private val titleValidator: TitleValidator,
    private val descriptionValidator: DescriptionValidator
) {
    data class Input(val title: String, val description: String, val authorId: String)
    data class ValidationResult(
        val titleError: String? = null,
        val descriptionError: String? = null,
        val isSuccess: Boolean = false
    )

    suspend fun execute(input: Input): ValidationResult {
        val titleError = titleValidator.getErrorMessage(input.title)
        val descriptionError = descriptionValidator.getErrorMessage(input.description)

        if (titleError != null || descriptionError != null) {
            return ValidationResult(titleError, descriptionError)
        }

        return try {
            val topic = TopicEntity(
                title = input.title,
                description = input.description,
                authorId = input.authorId
            )
            topicRepository.createTopic(topic)
            ValidationResult(isSuccess = true)
        } catch (_: Exception) {
            ValidationResult()
        }
    }
}
