package ru.itis.androidpractice.features.topic.data.remote.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity
import ru.itis.androidpractice.features.topic.domain.repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : TopicRepository {
    override suspend fun createTopic(topic: TopicEntity) = try {
        val doc = firestore.collection("topics").document()
        val id = doc.id
        val topicWithId = topic.copy(id = id)
        doc.set(topicWithId).await()
        Result.success(id)
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }

    override suspend fun getTopicById(id: String): Result<TopicEntity> = try {
        val doc = firestore.collection("topics").document(id).get().await()
        val topic = doc.toObject(TopicEntity::class.java)
        if (topic != null) {
            if (topic.id == doc.id) {
                Result.success(topic)
            } else {
                Result.failure(IllegalStateException("Document ID and internal ID mismatch"))
            }
        } else {
            Result.failure(NullPointerException("Topic not found"))
        }
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }
}