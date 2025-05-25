package ru.itis.androidpractice.features.topic.common.validators

import ru.itis.androidpractice.features.topic.common.validators.errormessages.ErrorMessages
import ru.itis.androidpractice.features.topic.domain.validation.CommentValidator
import javax.inject.Inject

class DefaultCommentValidator @Inject constructor() : CommentValidator {
    override fun isValid(comment: String): Boolean = comment.length in 1..2000

    override fun getErrorMessage(comment: String): String? {
        return when {
            comment.length > 2000 -> ErrorMessages.COMMENT_TOO_LONG
            else -> null
        }
    }
}