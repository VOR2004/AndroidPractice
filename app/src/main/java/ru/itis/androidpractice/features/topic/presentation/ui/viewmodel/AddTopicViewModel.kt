package ru.itis.androidpractice.features.topic.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.features.auth.domain.usecases.CheckInternetUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.AddTopicUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetIdUseCase
import ru.itis.androidpractice.features.topic.presentation.ui.screenstates.AddTopicState
import javax.inject.Inject

@HiltViewModel
class AddTopicViewModel @Inject constructor(
    private val addTopicUseCase: AddTopicUseCase,
    private val checkInternetUseCase: CheckInternetUseCase,
    private val getIdUseCase: GetIdUseCase
) : BaseViewModel<AddTopicState>(AddTopicState()) {

    fun onTitleChanged(newTitle: String) {
        viewState = viewState.copy(title = newTitle, titleError = null)
    }

    fun onDescriptionChanged(newDescription: String) {
        viewState = viewState.copy(description = newDescription, descriptionError = null)
    }

    fun addTopic() {
        viewModelScope.launch {
            val isConnected = checkInternetUseCase()

            if (!isConnected) {
                viewState = viewState.copy(showNoConnectionBanner = true)
                return@launch
            }

            val result = addTopicUseCase.execute(
                AddTopicUseCase.Input(
                    title = viewState.title,
                    description = viewState.description,
                    authorId = getIdUseCase.execute()
                )
            )

            viewState = viewState.copy(
                titleError = result.titleError,
                descriptionError = result.descriptionError
            )
        }
    }

    fun dismissNoConnectionBanner() {
        viewState = viewState.copy(showNoConnectionBanner = false)
    }
}
