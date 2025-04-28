package ru.itis.androidpractice.presentation.ui.screenstates

data class RegisterState(

    val textLogin: String = "",

    val textPassword: String = "",

    val textNickname: String = "",

    val loginError: String? = null,

    val passwordError: String? = null,

    val nicknameError: String? = null,

    val showNoConnectionBanner: Boolean = false,
)