package ru.itis.androidpractice.core.user.data.remote.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.auth.data.remote.mappers.UserFireBaseMapper
import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import ru.itis.androidpractice.core.user.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.features.auth.data.remote.entities.UserFirebaseEntity
import javax.inject.Inject
import ru.itis.androidpractice.features.auth.data.remote.utils.SafeCall.safeCall

class UserRemoteDataSourceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val mapper: UserFireBaseMapper
) : UserRemoteDataSource {

    private val usersCollection = firestore.collection("users")

    override suspend fun insertUser(user: BaseUserModel): Result<Unit> = safeCall {
        usersCollection.document(user.id)
            .set(mapper.toFirebaseUserEntity(user))
            .await()
        Unit
    }

    override suspend fun getUser(id: String): Result<BaseUserModel?> = safeCall {
        val snapshot = usersCollection.document(id).get().await()
        snapshot.toObject(UserFirebaseEntity::class.java)
        ?.let(mapper::toBaseUserModel)
    }

    override suspend fun getUserByEmail(email: String): Result<BaseUserModel?> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()

        snapshot.documents.firstOrNull()
            ?.toObject(UserFirebaseEntity::class.java)
            ?.let(mapper::toBaseUserModel)
    }

    override suspend fun getUserByUsername(name: String): Result<BaseUserModel?> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("username", name)
            .limit(1)
            .get()
            .await()

        snapshot.documents.firstOrNull()
            ?.toObject(UserFirebaseEntity::class.java)
            ?.let(mapper::toBaseUserModel)
    }

    override suspend fun isEmailTaken(email: String): Result<Boolean> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()

        !snapshot.isEmpty
    }

    override suspend fun isUsernameTaken(username: String): Result<Boolean> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("username", username)
            .limit(1)
            .get()
            .await()

        !snapshot.isEmpty
    }

    override suspend fun getUsername(userId: String): Result<String?> = safeCall {
        val document = usersCollection.document(userId).get().await()
        document.getString("username")
    }

    override suspend fun getRatingByUserId(userId: String): Result<Int> {
        return try {
            val doc = firestore.collection("user_ratings")
                .document(userId)
                .get()
                .await()
            val rating = doc.getLong("rating")?.toInt() ?: 0
            Result.success(rating)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
