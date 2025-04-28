package ru.itis.androidpractice.presentation.ui.screenstates

data class SignInState(

    val login: String = "",

    val password: String = "",

    val loginError: String? = null,

    val passwordError: String? = null,
)
