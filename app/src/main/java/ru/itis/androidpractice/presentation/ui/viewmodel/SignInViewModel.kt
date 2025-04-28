package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.SignInUseCase
import ru.itis.androidpractice.presentation.ui.screenstates.SignInState
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : BaseViewModel<SignInState>(SignInState()) {

    fun onLoginChanged(newLogin: String) {
        viewState = viewState.copy(
            login = newLogin,
            loginError = null
        )
    }

    fun onPasswordChanged(newPassword: String) {
        viewState = viewState.copy(
            password = newPassword,
            passwordError = null
        )
    }

    fun signIn(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = signInUseCase.execute(
                SignInUseCase.Input(
                    login = viewState.login,
                    password = viewState.password
                )
            )

            viewState = viewState.copy(
                loginError = result.loginError,
                passwordError = result.passwordError,
                password = if (result.isSuccess) viewState.password else ""
            )

            if (result.isSuccess) {
                onSuccess()
            }
        }
    }
}

