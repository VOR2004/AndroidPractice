package ru.itis.androidpractice.core.validators

import ru.itis.androidpractice.core.validators.errormessages.ErrorMessages
import ru.itis.androidpractice.domain.validation.EmailValidator
import javax.inject.Inject

class DefaultEmailValidator @Inject constructor() : EmailValidator {
    private val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")

    override fun isValid(email: String) = emailRegex.matches(email)

    override fun getErrorMessage(email: String): String? {
        return if (!isValid(email)) {
            ErrorMessages.INCORRECT_EMAIL
        } else null
    }
}