package ru.itis.androidpractice.features.topic.data.remote.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
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
}