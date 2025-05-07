package ru.itis.androidpractice.features.auth.domain.validation

interface UsernameValidator {
    fun isValid(username: String): Boolean
    fun getErrorMessage(username: String): String?
}