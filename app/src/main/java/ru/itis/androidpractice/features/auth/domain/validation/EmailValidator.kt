package ru.itis.androidpractice.features.auth.domain.validation

interface EmailValidator {
    fun isValid(email: String): Boolean
    fun getErrorMessage(email: String): String?
}
