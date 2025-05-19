package ru.itis.androidpractice.features.topic.common.validators

import ru.itis.androidpractice.features.topic.common.validators.errormessages.ErrorMessages
import ru.itis.androidpractice.features.topic.domain.validation.DescriptionValidator
import javax.inject.Inject

class DefaultDescriptionValidator @Inject constructor() : DescriptionValidator {
    override fun isValid(description: String): Boolean = description.length in 1..2000

    override fun getErrorMessage(description: String): String? {
        return when {
            description.isBlank() -> ErrorMessages.EMPTY_DESCRIPTION
            description.length > 2000 -> ErrorMessages.DESCRIPTION_TOO_LONG
            else -> null
        }
    }
}