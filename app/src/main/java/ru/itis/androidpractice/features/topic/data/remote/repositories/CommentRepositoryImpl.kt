package ru.itis.androidpractice.features.topic.data.remote.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.domain.repositories.CommentRepository
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : CommentRepository {
    override suspend fun addComment(comment: CommentEntity): Result<Unit> = try {
        val doc = firestore.collection("comments").document()
        val id = doc.id
        val commentWithId = comment.copy(id = id)
        doc.set(commentWithId).await()
        Result.success(Unit)
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }

    override suspend fun getCommentsForTopic(topicId: String): Result<List<CommentEntity>> = try {
        val doc = firestore.collection("comments")
            .whereEqualTo("topicId", topicId)
            .whereEqualTo("deleted", false)
//            .orderBy("createdAt")
            .get()
            .await()

        val comments = doc.toObjects(CommentEntity::class.java)
        Result.success(comments)
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }
}