package ru.itis.androidpractice.features.topic.domain.usecases

import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import javax.inject.Inject

class GetIdUseCase @Inject constructor(
    private val authService: FirebaseAuthService
) {
    suspend fun execute(): String {
        val id = authService.getCurrentUserId()
        if (id != null) {
            return id
        } else {
            throw RuntimeException()
        }
    }
}