package ru.itis.androidpractice.features.topic.domain.repositories

import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity

interface TopicRepository {
    suspend fun createTopic(topic: TopicEntity): Result<String>
    suspend fun getTopicById(id: String): Result<TopicEntity>
}