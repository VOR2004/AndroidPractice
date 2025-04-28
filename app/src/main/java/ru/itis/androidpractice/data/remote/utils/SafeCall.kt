package ru.itis.androidpractice.data.remote.utils

import com.google.firebase.firestore.FirebaseFirestoreException

object SafeCall {
    inline fun <T> safeCall(action: () -> T): Result<T> {
        return try {
            Result.success(action())
        } catch (e: FirebaseFirestoreException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}