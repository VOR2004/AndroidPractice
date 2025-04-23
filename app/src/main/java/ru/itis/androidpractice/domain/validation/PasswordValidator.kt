package ru.itis.androidpractice.domain.validation

interface PasswordValidator {
    fun isValid(password: String): Boolean
    fun getErrorMessage(password: String): String?
}