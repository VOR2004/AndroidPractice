package ru.itis.androidpractice.domain.validation

interface UsernameValidator {
    fun isValid(username: String): Boolean
    fun getErrorMessage(username: String): String?
}