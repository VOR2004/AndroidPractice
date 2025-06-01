package ru.itis.androidpractice.features.topic.presentation.ui.viewmodel

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.domain.usecases.AddCommentUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.AddReplyUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetCommentsByTopicIdUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetCurrentIdUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetTopicByIdUseCase
import ru.itis.androidpractice.features.topic.presentation.ui.screenstates.TopicDetailsState
import javax.inject.Inject

@HiltViewModel
class TopicDetailsViewModel @Inject constructor(
    private val addCommentUseCase: AddCommentUseCase,
    private val getTopicByIdUseCase: GetTopicByIdUseCase,
    private val getCurrentIdUseCase: GetCurrentIdUseCase,
    private val getCommentsByTopicIdUseCase: GetCommentsByTopicIdUseCase,
    private val addReplyUseCase: AddReplyUseCase,
) : BaseViewModel<TopicDetailsState>(TopicDetailsState()) {

    fun onReplyClick(comment: CommentEntity) {
        viewState = viewState.copy(
            replyToComment = comment
        )
    }

    fun clearReply() {
        viewState = viewState.copy(
            replyToComment = null
        )
    }

    fun onCommentTextChanged(text: TextFieldValue) {
        viewState = viewState.copy(currentCommentText = text, commentError = null)
    }

    fun sendCommentOrReply(topicId: String) {
        val text = viewState.currentCommentText.text
        val replyTarget = viewState.replyToComment

        viewModelScope.launch {
            val authorId = getCurrentIdUseCase.execute()

            if (replyTarget != null) {

                val result = addReplyUseCase.execute(
                    AddReplyUseCase.Input(
                        commentId = replyTarget.id,
                        authorId = authorId,
                        authorName = replyTarget.authorId,
                        text = text,
                        replyToAuthorName = replyTarget.authorId
                    )
                )
                viewState = viewState.copy(commentError = result.replyError)
                if (result.isSuccess) {
                    viewState = viewState.copy(
                        currentCommentText = TextFieldValue(""),
                        replyToComment = null
                    )
                    loadComments(topicId)
                }
            } else {
                val result = addCommentUseCase.execute(
                    AddCommentUseCase.Input(
                        topicId = topicId,
                        authorId = authorId,
                        text = text
                    )
                )
                viewState = viewState.copy(commentError = result.commentError)
                if (result.isSuccess) {
                    viewState = viewState.copy(
                        currentCommentText = TextFieldValue("")
                    )
                    loadComments(topicId)
                }
            }
        }
    }

    fun refreshAndLoadTopic(id: String) {
        viewModelScope.launch {
            viewState = viewState.copy(isLoading = true)
            val result = getTopicByIdUseCase(id)
            if (result.isSuccess) {
                val topic = result.getOrNull()
                topic?.let {
                    viewState = viewState.copy(
                        title = it.title,
                        description = it.description
                    )
                    loadComments(it.id)
                }
            }
            viewState = viewState.copy(isLoading = false)
        }
    }

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
                    loadComments(it.id)
                }
            }
        }
    }

    private suspend fun loadComments(topicId: String) {
        val commentsResult = getCommentsByTopicIdUseCase.execute(topicId)
        if (commentsResult.isSuccess) {
            viewState = viewState.copy(
                comments = commentsResult.getOrNull()?.toImmutableList() ?: persistentListOf()
            )
        }
    }
}
