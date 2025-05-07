package ru.itis.androidpractice.features.auth.common.validators

import ru.itis.androidpractice.features.auth.common.validators.errormessages.ErrorMessages
import ru.itis.androidpractice.features.auth.domain.validation.UsernameValidator
import javax.inject.Inject

class DefaultNicknameValidator @Inject constructor() : UsernameValidator {
    override fun isValid(username: String): Boolean {
        return username.length in 3..20 &&
                username.all { it.isLetterOrDigit() || it == '_' }
    }

    override fun getErrorMessage(username: String): String? {
        return when {
            username.length < 3 -> ErrorMessages.SHORT_NICK
            username.length > 20 -> ErrorMessages.LONG_NICK
            !username.all { it.isLetterOrDigit() || it == '_' } -> ErrorMessages.INCORRECT_NICK
            else -> null
        }
    }
}