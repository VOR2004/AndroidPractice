package ru.itis.androidpractice.features.topic.presentation.ui.screenstates

data class AddTopicState(
    val title: String = "",
    val description: String = "",
    val titleError: String? = null,
    val descriptionError: String? = null,
    val showNoConnectionBanner: Boolean = false
)