package ru.itis.androidpractice.core.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    object Register: Routes()
    @Serializable
    object SignIn: Routes()
    @Serializable
    object Main: Routes()
    @Serializable
    object Profile: Routes()
    @Serializable
    object People: Routes()
}