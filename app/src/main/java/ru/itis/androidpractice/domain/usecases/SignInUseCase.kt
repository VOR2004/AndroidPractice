package ru.itis.androidpractice.domain.usecases

import ru.itis.androidpractice.core.hasher.PasswordHasher
import ru.itis.androidpractice.domain.repositories.UserRepository
import ru.itis.androidpractice.domain.usecases.messageconstants.AuthConstants
import ru.itis.androidpractice.domain.validation.EmailValidator
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val userRepository: UserRepository,
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
        if (loginError != null) {
            return SignInResult(loginError = loginError)
        }

        val hash = userRepository.getHashPasswordByEmail(input.login)
            ?: return SignInResult(passwordError = AuthConstants.INCORRECT_INPUT)

        if (!PasswordHasher.verify(input.password, hash)) {
            return SignInResult(passwordError = AuthConstants.INCORRECT_INPUT)
        }

        return SignInResult(isSuccess = true)
    }
}
