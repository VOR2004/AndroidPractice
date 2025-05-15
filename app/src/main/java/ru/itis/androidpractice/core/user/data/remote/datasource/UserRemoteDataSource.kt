package ru.itis.androidpractice.core.user.data.remote.datasource

import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel

interface UserRemoteDataSource {
    suspend fun insertUser(user: BaseUserModel): Result<Unit>
    suspend fun getUser(id: String): Result<BaseUserModel?>
    suspend fun getUserByEmail(email: String): Result<BaseUserModel?>
    suspend fun isEmailTaken(email: String): Result<Boolean>
    suspend fun isUsernameTaken(username: String): Result<Boolean>
    suspend fun getUsername(userId: String): Result<String?>
}