package ru.itis.androidpractice.features.profile.domain

import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val authService: FirebaseAuthService
) {
    suspend fun execute() {
        authService.signOut()
    }
}