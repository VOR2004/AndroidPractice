package ru.itis.androidpractice.features.topic.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.features.topic.domain.usecases.AddCommentUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetCurrentIdUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetTopicByIdUseCase
import ru.itis.androidpractice.features.topic.presentation.ui.screenstates.TopicDetailsState
import javax.inject.Inject

@HiltViewModel
class TopicDetailsViewModel @Inject constructor(
    private val addCommentUseCase: AddCommentUseCase,
    private val getTopicByIdUseCase: GetTopicByIdUseCase,
    private val getCurrentIdUseCase: GetCurrentIdUseCase,
) : BaseViewModel<TopicDetailsState>(TopicDetailsState()) {

    fun loadTopic(id: String) {
        viewModelScope.launch {
            val result = getTopicByIdUseCase(id)
                if (result.isSuccess) {
                    val topic = result.getOrNull()
                    topic?.let {
                        viewState = viewState.copy(
                            title = it.title,
                            description = it.description
                        )
                    }
                }
//                else {
                    ///////////
//            }
        }
    }

    fun onCommentTextChanged(text: String) {
        viewState = viewState.copy(currentCommentText = text, commentError = null)
    }

    fun addComment(topicId: String) {
        val text = viewState.currentCommentText

        viewModelScope.launch {
            val authorId = getCurrentIdUseCase.execute()
            val result = addCommentUseCase.execute(
                AddCommentUseCase.Input(
                    topicId = topicId,
                    authorId = authorId,
                    text = text
                )
            )

            viewState = viewState.copy(
                commentError = result.commentError
            )

            if (result.isSuccess) {
                viewState = viewState.copy(currentCommentText = "")
            }
        }
    }


}