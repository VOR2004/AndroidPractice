package ru.itis.androidpractice.data.remote.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.data.remote.mappers.UserMappers.toFirebaseUser
import ru.itis.androidpractice.data.remote.mappers.UserMappers.toUserEntity
import ru.itis.androidpractice.data.remote.models.FirebaseUser
import javax.inject.Inject
import ru.itis.androidpractice.data.remote.utils.SafeCall.safeCall

class UserRemoteDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore
) : UserRemoteDataSource {

    private val usersCollection = firestore.collection("users")

    override suspend fun insertUser(user: UserEntity): Result<Unit> = safeCall {
        usersCollection.document(user.id)
            .set(user.toFirebaseUser())
            .await()
        Unit
    }

    override suspend fun getUser(id: String): Result<UserEntity?> = safeCall {
        val snapshot = usersCollection.document(id).get().await()
        snapshot.toObject(FirebaseUser::class.java)?.toUserEntity()
    }

    override suspend fun getUserByEmail(email: String): Result<UserEntity?> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get()
            .await()

        snapshot.documents.firstOrNull()
            ?.toObject(FirebaseUser::class.java)
            ?.toUserEntity()
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
