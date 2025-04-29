package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.CheckInternetUseCase
import ru.itis.androidpractice.domain.usecases.SignInUseCase
import ru.itis.androidpractice.presentation.ui.screenstates.SignInState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val checkInternetUseCase: CheckInternetUseCase
) : BaseViewModel<SignInState>(SignInState()) {

    fun onLoginChanged(newLogin: String) {
        viewState = viewState.copy(
            login = newLogin,
            loginError = null
        )
    }

    fun onPasswordChanged(newPassword: String) {
        viewState = viewState.copy(
            textPassword = newPassword,
            passwordError = null
        )
    }

    fun signIn(onSuccess: () -> Unit) {
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

            val result = signInUseCase.execute(
                SignInUseCase.Input(
                    login = viewState.login,
                    password = viewState.textPassword
                )
            )

            viewState = viewState.copy(
                loginError = result.loginError,
                passwordError = result.passwordError,
                textPassword = if (result.isSuccess) viewState.textPassword else ""
            )

            if (result.isSuccess) onSuccess()
        }
    }

    fun togglePasswordVisibility() {
        viewState = viewState.copy(
            passwordVisible = !viewState.passwordVisible
        )
    }

    fun dismissNoConnectionBanner() {
        viewState = viewState.copy(
            showNoConnectionBanner = false
        )
    }
}

