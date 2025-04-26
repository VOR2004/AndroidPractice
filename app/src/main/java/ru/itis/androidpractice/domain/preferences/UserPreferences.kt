package ru.itis.androidpractice.domain.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val isSignedInFlow: Flow<Boolean>
    suspend fun setSignedIn(signedIn: Boolean)
    suspend fun clear()
}