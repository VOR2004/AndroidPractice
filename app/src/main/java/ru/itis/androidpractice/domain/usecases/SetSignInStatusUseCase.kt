package ru.itis.androidpractice.domain.usecases

import ru.itis.androidpractice.domain.repositories.UserSessionRepository
import javax.inject.Inject

class SetSignInStatusUseCase @Inject constructor(
    private val repository: UserSessionRepository
) {
    suspend operator fun invoke(signedIn: Boolean) = repository.setSignedIn(signedIn)
}