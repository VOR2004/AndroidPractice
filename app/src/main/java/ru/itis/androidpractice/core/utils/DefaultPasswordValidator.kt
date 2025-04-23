package ru.itis.androidpractice.core.utils

import ru.itis.androidpractice.core.utils.errormessages.ErrorMessages
import ru.itis.androidpractice.domain.validation.PasswordValidator
import javax.inject.Inject

class DefaultPasswordValidator @Inject constructor() : PasswordValidator {
        override fun isValid(password: String): Boolean {
        return password.length >= 8 &&
                password.any { it.isDigit() } &&
                password.any { it.isUpperCase() } &&
                password.any { it.isLowerCase() }
    }

    override fun getErrorMessage(password: String): String? {
        return when {
            password.length < 8 -> ErrorMessages.SHORT_PASSWORD
            !password.any { it.isDigit() } -> ErrorMessages.PASSWORD_NO_NUM
            !password.any { it.isUpperCase() } -> ErrorMessages.PASSWORD_NO_UPPER
            !password.any { it.isLowerCase() } -> ErrorMessages.PASSWORD_NO_LOWER
            else -> null
        }
    }
}