package ru.itis.androidpractice.features.topic.presentation.ui.screenstates

data class TopicDetailsState(
    val title: String = "",
    val description: String = "",
    val currentCommentText: String = "",
    val commentError: String? = null
)