package ru.itis.androidpractice.presentation.ui.screenstates

data class SignInState(

    val login: String = "",

    val textPassword: String = "",

    val loginError: String? = null,

    val passwordError: String? = null,

    val passwordVisible: Boolean = false
)
