package ru.itis.androidpractice.features.topic.data.remote.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentLikeEntity
import ru.itis.androidpractice.features.topic.domain.repositories.CommentLikeRepository
import javax.inject.Inject

class CommentLikeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): CommentLikeRepository {

    override suspend fun toggleLike(commentId: String, userId: String): Result<Boolean> = try {
        val commentRef = firestore.collection("comments").document(commentId)
        val likeRef = commentRef.collection("likes").document(userId)

        val commentSnap = commentRef.get().await()
        val authorId = commentSnap.getString("authorId")
            ?: return Result.failure(IllegalStateException("authorId is missing"))

        val userRatingRef = firestore.collection("user_ratings").document(authorId)

        val wasLiked = likeRef.get().await().exists()

        firestore.runTransaction { tran ->
            if (wasLiked) {
                tran.delete(likeRef)
                tran.update(commentRef, "rating", com.google.firebase.firestore.FieldValue.increment(-1))
                tran.set(userRatingRef, mapOf("userId" to authorId), SetOptions.merge())
                tran.update(userRatingRef, "rating", com.google.firebase.firestore.FieldValue.increment(-1))

                false
            } else {
                tran.set(likeRef, CommentLikeEntity(commentId, userId))
                tran.update(commentRef, "rating", com.google.firebase.firestore.FieldValue.increment(1))
                tran.set(userRatingRef, mapOf("userId" to authorId), SetOptions.merge())
                tran.update(userRatingRef, "rating", com.google.firebase.firestore.FieldValue.increment(1))

                true
            }
        }.await()

        Result.success(!wasLiked)

    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }
}
