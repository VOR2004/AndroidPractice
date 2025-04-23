package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.RegisterUseCase

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var textLogin: String = ""
    var textPassword: String = ""
    var textNickname: String = ""

    var loginError: String? by mutableStateOf(null)
        private set
    var passwordError: String? by mutableStateOf(null)
        private set
    var nicknameError: String? by mutableStateOf(null)
        private set

    fun onLoginChanged(newLogin: String) {
        textLogin = newLogin
        if (loginError != null) loginError = null
    }

    fun onPasswordChanged(newPassword: String) {
        textPassword = newPassword
        if (passwordError != null) passwordError = null
    }

    fun onNicknameChanged(newNickname: String) {
        textNickname = newNickname
        if (nicknameError != null) nicknameError = null
    }

    fun registerUser(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = registerUseCase.execute(
                RegisterUseCase.Input(
                    login = textLogin,
                    password = textPassword,
                    nickname = textNickname
                )
            )

            loginError = result.loginError
            passwordError = result.passwordError
            nicknameError = result.nicknameError

            if (result.isSuccess) {
                onSuccess()
            }
        }
    }
}

