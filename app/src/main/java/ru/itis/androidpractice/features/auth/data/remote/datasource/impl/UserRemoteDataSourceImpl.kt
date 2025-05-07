package ru.itis.androidpractice.features.auth.data.remote.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.features.auth.data.remote.mappers.UserFireBaseMapper
import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import ru.itis.androidpractice.features.auth.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.features.auth.data.remote.entities.UserFirebaseEntity
import javax.inject.Inject
import ru.itis.androidpractice.features.auth.data.remote.utils.SafeCall.safeCall

class UserRemoteDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore,
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
}
