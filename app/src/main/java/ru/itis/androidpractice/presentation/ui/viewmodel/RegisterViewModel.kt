package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.RegisterUseCase
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    var textLogin by mutableStateOf("")
        private set

    var textPassword by mutableStateOf("")
        private set

    var textNickname by mutableStateOf("")
        private set

    var loginError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var nicknameError by mutableStateOf<String?>(null)
        private set

    fun onLoginChanged(newLogin: String) {
        textLogin = newLogin
        loginError = null
    }

    fun onPasswordChanged(newPassword: String) {
        textPassword = newPassword
        passwordError = null
    }

    fun onNicknameChanged(newNickname: String) {
        textNickname = newNickname
        nicknameError = null
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
