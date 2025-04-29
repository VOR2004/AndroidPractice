package ru.itis.androidpractice.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Register
    @Serializable
    object SignIn
    @Serializable
    object Main
}