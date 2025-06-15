package ru.itis.androidpractice.core.session.data.repositories

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.core.session.domain.repositories.UserRatingRepository
import javax.inject.Inject

class UserRatingRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : UserRatingRepository {

    override suspend fun getUserRating(userId: String): Result<Int> = try {
        val userRatingRef = firestore.collection("user_ratings").document(userId)
        val snapshot = userRatingRef.get().await()
        val rating = snapshot.getLong("rating")?.toInt() ?: 0
        Result.success(rating)
    } catch (e: FirebaseFirestoreException) {
        Firebase.crashlytics.recordException(e)
        Result.failure(e)
    }
}
