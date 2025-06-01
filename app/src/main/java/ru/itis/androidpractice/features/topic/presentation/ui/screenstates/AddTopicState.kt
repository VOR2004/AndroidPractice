package ru.itis.androidpractice.features.topic.presentation.ui.screenstates

import androidx.compose.runtime.Immutable

@Immutable
data class AddTopicState(
    val title: String = "",
    val description: String = "",
    val titleError: String? = null,
    val descriptionError: String? = null,
    val showNoConnectionBanner: Boolean = false
)