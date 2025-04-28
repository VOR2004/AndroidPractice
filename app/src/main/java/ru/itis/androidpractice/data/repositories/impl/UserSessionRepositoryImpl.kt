package ru.itis.androidpractice.data.repositories.impl

import kotlinx.coroutines.flow.Flow
import ru.itis.androidpractice.data.local.preferences.UserPreferences
import ru.itis.androidpractice.domain.repositories.UserSessionRepository
import javax.inject.Inject

class UserSessionRepositoryImpl @Inject constructor(
    private val preferences: UserPreferences
) : UserSessionRepository {

    override fun isSignedIn(): Flow<Boolean> = preferences.isSignedInFlow

    override suspend fun setSignedIn(signedIn: Boolean) {
        preferences.setSignedIn(signedIn)
    }

    override suspend fun clearSession() {
        preferences.clear()
    }
}