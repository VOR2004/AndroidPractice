package ru.itis.androidpractice.core.session.domain.usecases

import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import ru.itis.androidpractice.core.user.domain.repositories.UserRepository
import javax.inject.Inject

class GetNameUseCase @Inject constructor(
    private val authService: FirebaseAuthService
) {
    fun invoke(): String {
        return authService.getCurrentUserName() ?: ""
    }
}