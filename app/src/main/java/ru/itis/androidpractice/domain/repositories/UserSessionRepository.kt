package ru.itis.androidpractice.domain.repositories

import kotlinx.coroutines.flow.Flow

interface UserSessionRepository {
    fun isSignedIn(): Flow<Boolean>
    suspend fun setSignedIn(signedIn: Boolean)
    suspend fun clearSession()
}