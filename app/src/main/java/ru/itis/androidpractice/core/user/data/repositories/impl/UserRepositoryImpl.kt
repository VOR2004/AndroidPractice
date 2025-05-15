package ru.itis.androidpractice.core.user.data.repositories.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import ru.itis.androidpractice.core.user.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.core.user.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.core.user.domain.repositories.UserRepository
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

    override suspend fun getName(id: String): String? {
        return withContext(Dispatchers.IO) {
            val name = userLocalDataSource.getUsernameById(id)
            if (name != null) {
                name
            } else {
                val remoteResult = userRemoteDataSource.getUsername(id)
                remoteResult.getOrNull()
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
}
