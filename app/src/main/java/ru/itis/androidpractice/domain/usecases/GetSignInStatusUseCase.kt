package ru.itis.androidpractice.domain.usecases

import kotlinx.coroutines.flow.Flow
import ru.itis.androidpractice.domain.repositories.UserSessionRepository
import javax.inject.Inject

class GetSignInStatusUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    operator fun invoke(): Flow<Boolean> = repository.isSignedIn()
}