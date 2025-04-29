package ru.itis.androidpractice.data.repositories.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.androidpractice.data.common.model.BaseUserModel
import ru.itis.androidpractice.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun saveUser(user: BaseUserModel) {
        withContext(Dispatchers.IO) {
            val remoteResult = userRemoteDataSource.insertUser(user)
            remoteResult.onSuccess {
                userLocalDataSource.insertUser(user)
            }
        }
    }

    override suspend fun getUser(id: String): BaseUserModel? {
        return withContext(Dispatchers.IO) {
            val localUser = userLocalDataSource.getUser(id)
            if (localUser != null) {
                localUser
            } else {
                val remoteResult = userRemoteDataSource.getUser(id)
                remoteResult.getOrNull()?.also { user ->
                    userLocalDataSource.insertUser(user)
                }
            }
        }
    }

    override suspend fun getUserByEmail(email: String): BaseUserModel? {
        return withContext(Dispatchers.IO) {
            val localUser = userLocalDataSource.getUserByEmail(email)
            if (localUser != null) {
                localUser
            } else {
                val remoteResult = userRemoteDataSource.getUserByEmail(email)
                remoteResult.getOrNull()?.also { user ->
                    userLocalDataSource.insertUser(user)
                }
            }
        }
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            val remoteResult = userRemoteDataSource.isEmailTaken(email)
            remoteResult.getOrDefault(false)
        }
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            val remoteResult = userRemoteDataSource.isUsernameTaken(username)
            remoteResult.getOrDefault(false)
        }
    }

    override suspend fun getHashPasswordByEmail(email: String): String? {
        return withContext(Dispatchers.IO) {
            userRemoteDataSource.getHashPasswordByEmail(email).getOrNull()
        }
    }
}
