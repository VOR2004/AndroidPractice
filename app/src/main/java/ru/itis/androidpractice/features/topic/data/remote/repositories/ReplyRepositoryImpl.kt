package ru.itis.androidpractice.features.topic.data.remote.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.topic.data.remote.entities.CommentEntity
import ru.itis.androidpractice.features.topic.data.remote.entities.ReplyEntity
import ru.itis.androidpractice.features.topic.domain.repositories.ReplyRepository
import javax.inject.Inject

class ReplyRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : ReplyRepository {
    override suspend fun addReply(reply: ReplyEntity): Result<Unit> = try {
        val doc = firestore.collection("replies").document()
        val id = doc.id
        val replyWithId = reply.copy(id = id)
        doc.set(replyWithId).await()
        Result.success(Unit)
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }

    override suspend fun getRepliesForComment(commentId: String): Result<List<ReplyEntity>> = try {
        val doc = firestore.collection("replies")
            .whereEqualTo("commentId", commentId)
            .whereEqualTo("deleted", false)
//            .orderBy("createdAt")
            .get()
            .await()

        val replies = doc.toObjects(ReplyEntity::class.java)
        Result.success(replies)
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }
}