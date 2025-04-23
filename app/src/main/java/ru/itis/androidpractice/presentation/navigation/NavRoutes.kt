package ru.itis.androidpractice.presentation.navigation

sealed class Routes(val route: String) {
    object Register : Routes("register")
    object SignIn : Routes("sign_in")
    object Main : Routes("main")
}