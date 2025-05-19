package ru.itis.androidpractice.features.topic.domain.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity
import ru.itis.androidpractice.features.topic.data.remote.repositories.TopicRepository
import javax.inject.Inject

class TopicRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : TopicRepository {
    override suspend fun createTopic(topic: TopicEntity) = try {
        firestore.collection("topics").add(topic).await()
        Result.success(Unit)
    } catch (e: FirebaseFirestoreException) {
        Result.failure(e)
    }
}