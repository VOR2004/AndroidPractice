package ru.itis.androidpractice.core.session.domain.usecases

import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import javax.inject.Inject

class GetCurrentIdUseCase @Inject constructor(
    private val authService: FirebaseAuthService
) {
    fun invoke(): String {
        return authService.getCurrentUserId() ?: ""
    }
}