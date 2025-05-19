package ru.itis.androidpractice.features.topic.data.remote.entities

import java.util.Date

data class TopicEntity(
    val id: String = "",
    val title: String,
    val description: String,
    val authorId: String,
    val createdAt: Date = Date()
)