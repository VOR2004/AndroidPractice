package ru.itis.androidpractice.data.remote.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.data.remote.datasource.UserRemoteDataSource
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore
) : UserRemoteDataSource {

    private val usersCollection = firestore.collection("users")

    override suspend fun insertUser(user: UserEntity) {
        usersCollection.document(user.id).set(user).await()
    }

    override suspend fun getUser(id: String): UserEntity? {
        val snapshot = usersCollection.document(id).get().await()
        return snapshot.toObject(UserEntity::class.java)
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()

        return snapshot.documents.firstOrNull()?.toObject(UserEntity::class.java)
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()

        return !snapshot.isEmpty
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        val snapshot = usersCollection
            .whereEqualTo("username", username)
            .limit(1)
            .get()
            .await()

        return !snapshot.isEmpty
    }
}