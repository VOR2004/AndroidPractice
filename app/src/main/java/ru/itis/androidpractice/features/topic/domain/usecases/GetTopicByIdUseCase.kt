package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity
import ru.itis.androidpractice.features.topic.domain.repositories.TopicRepository
import javax.inject.Inject

class GetTopicByIdUseCase @Inject constructor(
    private val repository: TopicRepository
) {
    suspend operator fun invoke(id: String): Result<TopicEntity> {
        return repository.getTopicById(id)
    }
}