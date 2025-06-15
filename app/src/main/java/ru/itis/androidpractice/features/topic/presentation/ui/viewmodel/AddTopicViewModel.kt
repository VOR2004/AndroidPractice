package ru.itis.androidpractice.features.topic.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.features.auth.domain.usecases.CheckInternetUseCase
import ru.itis.androidpractice.features.topic.common.validators.TextTrimmer
import ru.itis.androidpractice.features.topic.domain.usecases.AddTopicUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetCurrentIdUseCase
import ru.itis.androidpractice.features.topic.presentation.ui.screenstates.AddTopicState
import javax.inject.Inject

@HiltViewModel
class AddTopicViewModel @Inject constructor(
    private val addTopicUseCase: AddTopicUseCase,
    private val checkInternetUseCase: CheckInternetUseCase,
    private val getCurrentIdUseCase: GetCurrentIdUseCase
) : BaseViewModel<AddTopicState>(AddTopicState()) {

    fun onTitleChanged(newTitle: String) {
        viewState = viewState.copy(title = newTitle, titleError = null)
    }

    fun onDescriptionChanged(newDescription: String) {
        viewState = viewState.copy(description = newDescription, descriptionError = null)
    }

    fun addTopic(onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val isConnected = checkInternetUseCase()

            if (!isConnected) {
                viewState = viewState.copy(showNoConnectionBanner = true)
                return@launch
            }

            val result = addTopicUseCase.execute(
                AddTopicUseCase.Input(
                    title = TextTrimmer.trimExcessiveLineBreaks(viewState.title),
                    description = TextTrimmer.trimExcessiveLineBreaks(viewState.description),
                    authorId = getCurrentIdUseCase.execute()
                )
            )

            viewState = viewState.copy(
                titleError = result.titleError,
                descriptionError = result.descriptionError
            )

            result.topicId?.let { onSuccess(it) }
        }
    }


    fun dismissNoConnectionBanner() {
        viewState = viewState.copy(showNoConnectionBanner = false)
    }
}
