package ru.itis.androidpractice.core.session.domain.repositories

interface UserRatingRepository {
    suspend fun getUserRating(userId: String): Result<Int>
}