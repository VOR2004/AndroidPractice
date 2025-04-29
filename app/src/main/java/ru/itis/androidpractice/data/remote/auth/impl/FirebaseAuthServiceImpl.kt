package ru.itis.androidpractice.data.remote.auth.impl

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.domain.services.FirebaseAuthService
import javax.inject.Inject

class FirebaseAuthServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthService {

    override suspend fun signUp(email: String, password: String): String {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        return result.user?.uid ?: throw FirebaseAuthException("nf", "Not found")
    }

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override fun getCurrentUserId(): String? = auth.currentUser?.uid

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun observeAuthState(): Flow<Boolean> = callbackFlow {
        val listener = FirebaseAuth.AuthStateListener { auth ->
            trySend(auth.currentUser != null)
        }
        auth.addAuthStateListener(listener)
        awaitClose { auth.removeAuthStateListener(listener) }
    }
}