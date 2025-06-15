package ru.itis.androidpractice.features.main.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.features.main.presentation.ui.screenstates.MainState
import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity
import ru.itis.androidpractice.features.topic.domain.repositories.TopicRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val topicRepository: TopicRepository
) : BaseViewModel<MainState>(MainState()) {

    private var allTopics: List<TopicEntity> = emptyList()

    init {
        loadTopics()
    }

    fun loadTopics() {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val result = topicRepository.getTopics()
            if (result.isSuccess) {
                allTopics = result.getOrNull() ?: emptyList()
                viewState = viewState.copy(
                    topics = allTopics.toImmutableList(),
                    isLoading = false,
                )
            } else {
                viewState = viewState.copy(
                    topics = persistentListOf(),
                    isLoading = false,
                )
            }
        }
    }

    fun onSearchQueryChange(newQuery: String) {
        viewState = viewState.copy(
            searchQuery = newQuery,
        )
    }

    fun onSearchClick() {
        val query = viewState.searchQuery.trim()
        if (query.isEmpty()) {
            viewState = viewState.copy(
                topics = allTopics.toImmutableList()
            )
            return
        }

        val filtered = allTopics.filter { topic ->
            topic.title.contains(query, ignoreCase = true) ||
                    topic.description.contains(query, ignoreCase = true)
        }
        Firebase.crashlytics.setCustomKey("filtered_size", filtered.size)
        viewState = viewState.copy(
            topics = filtered.toImmutableList(),
        )
    }
}



