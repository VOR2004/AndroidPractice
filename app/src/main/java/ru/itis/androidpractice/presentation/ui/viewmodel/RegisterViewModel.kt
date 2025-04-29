package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.CheckInternetUseCase
import ru.itis.androidpractice.domain.usecases.RegisterUseCase
import ru.itis.androidpractice.presentation.ui.screenstates.RegisterState
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val checkInternetUseCase: CheckInternetUseCase
) : BaseViewModel<RegisterState>(RegisterState()) {

    fun onLoginChanged(newLogin: String) {
        viewState = viewState.copy(
            textLogin = newLogin,
            loginError = null
        )
    }

    fun onPasswordChanged(newPassword: String) {
        viewState = viewState.copy(
            textPassword = newPassword,
            passwordError = null
        )
    }

    fun onNicknameChanged(newNickname: String) {
        viewState = viewState.copy(
            textNickname = newNickname,
            nicknameError = null
        )
    }

    fun registerUser() {
        viewModelScope.launch {
            val isConnected = checkInternetUseCase.invoke()

            if (!isConnected) {
                viewState = viewState.copy(
                    showNoConnectionBanner = true
                )
                return@launch
            } else {
                viewState = viewState.copy(
                    showNoConnectionBanner = false
                )
            }

            val result = registerUseCase.execute(
                RegisterUseCase.Input(
                    login = viewState.textLogin,
                    password = viewState.textPassword,
                    nickname = viewState.textNickname
                )
            )

            viewState = viewState.copy(
                loginError = result.loginError,
                passwordError = result.passwordError,
                nicknameError = result.nicknameError
            )
        }
    }

    fun dismissNoConnectionBanner() {
        viewState = viewState.copy(
            showNoConnectionBanner = false
        )
    }

    fun togglePasswordVisibility() {
        viewState = viewState.copy(
            passwordVisible = !viewState.passwordVisible
        )
    }
}

