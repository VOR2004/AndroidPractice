package ru.itis.androidpractice.domain.validation

interface EmailValidator {
    fun isValid(email: String): Boolean
    fun getErrorMessage(email: String): String?
}
