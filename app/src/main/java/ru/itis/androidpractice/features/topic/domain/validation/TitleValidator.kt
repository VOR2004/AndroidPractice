package ru.itis.androidpractice.features.topic.domain.validation

interface TitleValidator {
    fun isValid(title: String): Boolean
    fun getErrorMessage(title: String): String?
}