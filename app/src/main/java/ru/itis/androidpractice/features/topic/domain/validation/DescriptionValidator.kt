package ru.itis.androidpractice.features.topic.domain.validation

interface DescriptionValidator {
    fun isValid(description: String): Boolean
    fun getErrorMessage(description: String): String?
}