package ru.itis.androidpractice.features.main.presentation.ui.screenstates

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity

data class MainState(
    val topics: ImmutableList<TopicEntity> = persistentListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val searchQuery: String = "",
)