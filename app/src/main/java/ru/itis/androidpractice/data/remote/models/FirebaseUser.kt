package ru.itis.androidpractice.data.remote.models

data class FirebaseUser(
    val id: String = "",
    val email: String = "",
    val hashPassword: String = "",
    val username: String = ""
)