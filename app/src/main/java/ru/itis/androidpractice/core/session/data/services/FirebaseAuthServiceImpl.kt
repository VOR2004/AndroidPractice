package ru.itis.androidpractice.core.session.data.services

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import javax.inject.Inject

class FirebaseAuthServiceImpl @Inject constructor(
    private val auth: FirebaseAuth
) : FirebaseAuthService {

    override suspend fun signUp(email: String, password: String, displayName: String): String {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val user = result.user ?: throw FirebaseAuthException("nf", "User not found")

        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(displayName)
            .build()

        user.updateProfile(profileUpdates).await()
        return user.uid
    }

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override fun getCurrentUserId(): String? = auth.currentUser?.uid

    override fun getCurrentUserName(): String? = auth.currentUser?.displayName

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