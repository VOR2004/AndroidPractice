package ru.itis.androidpractice.domain.usecases

import ru.itis.androidpractice.domain.repositories.UserSessionRepository
import javax.inject.Inject

class ClearSessionUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    suspend operator fun invoke() = repository.clearSession()
}