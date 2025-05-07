package ru.itis.androidpractice.features.auth.domain.usecases

import com.google.firebase.FirebaseException
import ru.itis.androidpractice.features.auth.domain.services.FirebaseAuthService
import ru.itis.androidpractice.features.auth.domain.usecases.messageconstants.AuthConstants
import ru.itis.androidpractice.features.auth.domain.validation.EmailValidator
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authService: FirebaseAuthService,
    private val emailValidator: EmailValidator
) {
    data class Input(val login: String, val password: String)
    data class SignInResult(
        val loginError: String? = null,
        val passwordError: String? = null,
        val isSuccess: Boolean = false
    )

    suspend fun execute(input: Input): SignInResult {
        val loginError = emailValidator.getErrorMessage(input.login)
        if (loginError != null) return SignInResult(loginError = loginError)

        return try {
            authService.signIn(input.login, input.password)
            SignInResult(isSuccess = true)
        } catch (_: FirebaseException) {
            SignInResult(passwordError = AuthConstants.INCORRECT_INPUT)
        }
    }
}
