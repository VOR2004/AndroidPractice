package ru.itis.androidpractice.features.topic.data.remote.repositories

import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity

interface TopicRepository {
    suspend fun createTopic(topic: TopicEntity): Result<Unit>
}