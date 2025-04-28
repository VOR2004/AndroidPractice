package ru.itis.androidpractice.data.common.model

data class BaseUserModel(
    val id: String = "",
    val email: String = "",
    val hashPassword: String = "",
    val username: String = ""
)