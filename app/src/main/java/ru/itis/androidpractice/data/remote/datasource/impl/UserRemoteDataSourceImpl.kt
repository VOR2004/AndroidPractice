package ru.itis.androidpractice.data.remote.datasource.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Source
import kotlinx.coroutines.tasks.await
import ru.itis.androidpractice.data.common.model.BaseUserModel
import ru.itis.androidpractice.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.data.remote.mappers.UserMappers.toBaseUserModel
import ru.itis.androidpractice.data.remote.mappers.UserMappers.toFirebaseUser
import ru.itis.androidpractice.data.remote.models.FirebaseUser
import javax.inject.Inject
import ru.itis.androidpractice.data.remote.utils.SafeCall.safeCall

class UserRemoteDataSourceImpl @Inject constructor(
    firestore: FirebaseFirestore
) : UserRemoteDataSource {

    private val usersCollection = firestore.collection("users")

    override suspend fun insertUser(user: BaseUserModel): Result<Unit> = safeCall {
        usersCollection.document(user.id)
            .set(user.toFirebaseUser())
            .await()
        Unit
    }

    override suspend fun getUser(id: String): Result<BaseUserModel?> = safeCall {
        val snapshot = usersCollection.document(id).get().await()
        snapshot.toObject(FirebaseUser::class.java)?.toBaseUserModel()
    }

    override suspend fun getUserByEmail(email: String): Result<BaseUserModel?> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get(Source.SERVER)
            .await()

        snapshot.documents.firstOrNull()
            ?.toObject(FirebaseUser::class.java)
            ?.toBaseUserModel()
    }

    override suspend fun isEmailTaken(email: String): Result<Boolean> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get(Source.SERVER)
            .await()

        !snapshot.isEmpty
    }

    override suspend fun isUsernameTaken(username: String): Result<Boolean> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("username", username)
            .limit(1)
            .get(Source.SERVER)
            .await()

        !snapshot.isEmpty
    }

    override suspend fun getHashPasswordByEmail(email: String): Result<String?> = safeCall {
        val snapshot = usersCollection
            .whereEqualTo("email", email)
            .limit(1)
            .get(Source.SERVER)
            .await()

        val hash = snapshot.documents.firstOrNull()
            ?.toObject(FirebaseUser::class.java)
            ?.hashPassword

        hash
    }
}
