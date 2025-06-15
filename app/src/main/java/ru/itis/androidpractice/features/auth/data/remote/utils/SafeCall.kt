package ru.itis.androidpractice.features.auth.data.remote.utils

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.firestore.FirebaseFirestoreException

object SafeCall {
    inline fun <T> safeCall(action: () -> T): Result<T> {
        return try {
            Result.success(action())
        } catch (e: FirebaseFirestoreException) {
            Firebase.crashlytics.recordException(e)
            Result.failure(e)
        } catch (e: Exception) {
            Firebase.crashlytics.recordException(e)
            Result.failure(e)
        }
    }
}