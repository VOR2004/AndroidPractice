package ru.itis.androidpractice.data.local.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    val isSignedInFlow: Flow<Boolean>
    suspend fun setSignedIn(signedIn: Boolean)
    suspend fun clear()
}