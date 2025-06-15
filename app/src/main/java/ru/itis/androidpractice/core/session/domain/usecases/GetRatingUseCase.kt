package ru.itis.androidpractice.core.session.domain.usecases

import ru.itis.androidpractice.core.session.domain.repositories.UserRatingRepository
import javax.inject.Inject

class GetRatingUseCase @Inject constructor(
    private val userRatingRepository: UserRatingRepository
) {
    suspend fun invoke(userId: String): String {
        return userRatingRepository.getUserRating(userId).getOrDefault(
            defaultValue = "0"
        ).toString()
    }
}