package ru.itis.androidpractice.domain.usecases

import ru.itis.androidpractice.core.utils.PasswordHasher
import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.domain.repositories.UserRepository
import ru.itis.androidpractice.domain.usecases.messageconstants.AuthConstants
import ru.itis.androidpractice.domain.validation.EmailValidator
import ru.itis.androidpractice.domain.validation.PasswordValidator
import ru.itis.androidpractice.domain.validation.UsernameValidator
import java.util.UUID
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher,
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

        val user = UserEntity(
            id = UUID.randomUUID().toString(),
            email = input.login,
            hashPassword = passwordHasher.hash(input.password),
            username = input.nickname
        )

        userRepository.saveUser(user)

        return ValidationResult(isSuccess = true)
    }
}
