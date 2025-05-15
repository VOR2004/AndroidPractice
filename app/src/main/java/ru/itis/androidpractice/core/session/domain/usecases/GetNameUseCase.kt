package ru.itis.androidpractice.core.session.domain.usecases

import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import ru.itis.androidpractice.core.user.domain.repositories.UserRepository
import javax.inject.Inject

class GetNameUseCase @Inject constructor(
    private val authService: FirebaseAuthService,
    private val userRepository: UserRepository
) {
    suspend fun invoke(): String {
        val id = authService.getCurrentUserId()
        return if (id != null) {
            val name = userRepository.getName(id)
            name ?: ""
        } else {
            ""
        }
    }
}