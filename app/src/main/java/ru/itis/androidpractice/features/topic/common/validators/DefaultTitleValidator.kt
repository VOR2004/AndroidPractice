package ru.itis.androidpractice.features.topic.common.validators

import ru.itis.androidpractice.features.topic.common.validators.errormessages.ErrorMessages
import ru.itis.androidpractice.features.topic.domain.validation.TitleValidator
import javax.inject.Inject

class DefaultTitleValidator @Inject constructor() : TitleValidator {
    override fun isValid(title: String): Boolean = title.length in 1..100

    override fun getErrorMessage(title: String): String? {
        return when {
            title.isBlank() -> ErrorMessages.EMPTY_TITLE
            title.length > 100 -> ErrorMessages.TITLE_TOO_LONG
            else -> null
        }
    }
}