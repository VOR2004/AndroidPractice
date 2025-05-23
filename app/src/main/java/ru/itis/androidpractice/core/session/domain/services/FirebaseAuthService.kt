package ru.itis.androidpractice.core.session.domain.services

import kotlinx.coroutines.flow.Flow

interface FirebaseAuthService {
    suspend fun signUp(email: String, password: String, displayName: String): String
    suspend fun signIn(email: String, password: String)
    fun getCurrentUserId(): String?
    suspend fun signOut()
    fun observeAuthState(): Flow<Boolean>
    fun getCurrentUserName(): String?
}