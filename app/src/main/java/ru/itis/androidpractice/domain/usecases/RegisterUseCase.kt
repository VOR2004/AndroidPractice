package ru.itis.androidpractice.domain.usecases

import com.google.firebase.FirebaseException
import ru.itis.androidpractice.data.common.model.BaseUserModel
import ru.itis.androidpractice.domain.services.FirebaseAuthService
import ru.itis.androidpractice.domain.repositories.UserRepository
import ru.itis.androidpractice.domain.usecases.messageconstants.AuthConstants
import ru.itis.androidpractice.domain.validation.EmailValidator
import ru.itis.androidpractice.domain.validation.PasswordValidator
import ru.itis.androidpractice.domain.validation.UsernameValidator
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authService: FirebaseAuthService,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
    private val usernameValidator: UsernameValidator
) {
    data class Input(val login: String, val password: String, val nickname: String)
    data class ValidationResult(
        val loginError: String? = null,
        val passwordError: String? = null,
        val nicknameError: String? = null,
        val isSuccess: Boolean = false
    )

    suspend fun execute(input: Input): ValidationResult {
        var loginError = emailValidator.getErrorMessage(input.login)
        val passwordError = passwordValidator.getErrorMessage(input.password)
        var nicknameError = usernameValidator.getErrorMessage(input.nickname)

        if (loginError == null && userRepository.isEmailTaken(input.login)) {
            loginError = AuthConstants.LOGIN_ERROR
        }

        if (nicknameError == null && userRepository.isUsernameTaken(input.nickname)) {
            nicknameError = AuthConstants.NICKNAME_ERROR
        }

        if (loginError != null || passwordError != null || nicknameError != null) {
            return ValidationResult(
                loginError = loginError,
                passwordError = passwordError,
                nicknameError = nicknameError
            )
        }

        return try {
            val userId = authService.signUp(input.login, input.password)
            val user = BaseUserModel(
                id = userId,
                email = input.login,
                username = input.nickname
            )
            userRepository.saveUser(user)
            ValidationResult(isSuccess = true)
        } catch (_: FirebaseException) {
            return ValidationResult(isSuccess = false)
        }
    }
}
