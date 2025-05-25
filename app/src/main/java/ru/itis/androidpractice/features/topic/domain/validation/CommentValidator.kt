package ru.itis.androidpractice.features.topic.domain.validation

interface CommentValidator {
    fun isValid(comment: String): Boolean
    fun getErrorMessage(comment: String): String?
}