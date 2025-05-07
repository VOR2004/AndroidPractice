package ru.itis.androidpractice.features.auth.presentation.ui.screenstates

data class SignInState(

    val login: String = "",

    val textPassword: String = "",

    val loginError: String? = null,

    val passwordError: String? = null,

    val passwordVisible: Boolean = false,

    val showNoConnectionBanner: Boolean = false,
)
