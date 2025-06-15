package ru.itis.androidpractice.features.topic.presentation.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.session.domain.usecases.GetNameUseCase
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.features.topic.common.validators.TextTrimmer
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.domain.usecases.AddCommentUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.AddLikeUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.AddReplyUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetCommentsByTopicIdUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetCurrentIdUseCase
import ru.itis.androidpractice.features.topic.domain.usecases.GetRepliesForCommentUseCase
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
    private val getRepliesForCommentUseCase: GetRepliesForCommentUseCase,
    private val getNameUseCase: GetNameUseCase,
    private val addLikeUseCase: AddLikeUseCase
) : BaseViewModel<TopicDetailsState>(TopicDetailsState()) {

    fun onReplyClick(comment: CommentEntity) {
        viewState = viewState.copy(
            replyToComment = comment
        )
    }

    suspend fun onLikeClick(commentId: String): Boolean {
        val userId = getCurrentIdUseCase.execute()
        return addLikeUseCase.execute(commentId, userId).getOrDefault(false)
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
                        text = TextTrimmer.trimExcessiveLineBreaks(text),
                        replyToAuthorName = replyTarget.authorId
                    )
                )
                viewState = viewState.copy(commentError = result.replyError)
                if (result.isSuccess) {
                    viewState = viewState.copy(
                        currentCommentText = TextFieldValue(""),
                        replyToComment = null
                    )
                    loadReplies(replyTarget.id)
                }
            } else {
                val result = addCommentUseCase.execute(
                    AddCommentUseCase.Input(
                        topicId = topicId,
                        authorId = authorId,
                        text = TextTrimmer.trimExcessiveLineBreaks(text),
                        authorName = getNameUseCase.invoke()
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

    fun toggleRepliesVisibility(commentId: String) {
        val isExpanded = viewState.expandedComments[commentId] ?: false
        if (isExpanded) {
            viewState.expandedComments.remove(commentId)
            viewState.visibleReplies.remove(commentId)
        } else {
            viewModelScope.launch {
                loadReplies(commentId)
                viewState.expandedComments[commentId] = true
            }
        }
    }

    private suspend fun loadComments(topicId: String) {
        val commentsResult = getCommentsByTopicIdUseCase.execute(topicId)
        if (commentsResult.isSuccess) {
            viewState.comments.clear()
            viewState.comments.addAll(commentsResult.getOrNull() ?: emptyList())
        }
    }

    suspend fun loadReplies(commentId: String) {
        val repliesResult = getRepliesForCommentUseCase.execute(commentId)
        if (repliesResult.isSuccess) {
            val repliesList = viewState.visibleReplies[commentId] ?: mutableStateListOf()
            repliesList.clear()
            repliesList.addAll(repliesResult.getOrNull() ?: emptyList())
            viewState.visibleReplies[commentId] = repliesList
        }

    }
}
