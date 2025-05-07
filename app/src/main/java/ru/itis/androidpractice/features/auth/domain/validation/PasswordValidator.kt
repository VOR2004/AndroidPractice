package ru.itis.androidpractice.features.auth.domain.validation

interface PasswordValidator {
    fun isValid(password: String): Boolean
    fun getErrorMessage(password: String): String?
}