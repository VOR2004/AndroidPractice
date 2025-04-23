package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    var login: String = ""
    var password: String = ""

    var loginError: String? by mutableStateOf(null)
        private set
    var passwordError: String? by mutableStateOf(null)
        private set

    fun onLoginChanged(value: String) {
        login = value
        if (loginError != null) loginError = null
    }

    fun onPasswordChanged(value: String) {
        password = value
        if (passwordError != null) passwordError = null
    }

    fun signIn(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = signInUseCase.execute(SignInUseCase.Input(login, password))

            loginError = result.loginError
            passwordError = result.passwordError

            if (result.isSuccess) {
                onSuccess()
            }
        }
    }
}
