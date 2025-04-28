package ru.itis.androidpractice.data.remote.datasource

import ru.itis.androidpractice.data.local.entities.UserEntity

interface UserRemoteDataSource {
    suspend fun insertUser(user: UserEntity): Result<Unit>
    suspend fun getUser(id: String): Result<UserEntity?>
    suspend fun getUserByEmail(email: String): Result<UserEntity?>
    suspend fun isEmailTaken(email: String): Result<Boolean>
    suspend fun isUsernameTaken(username: String): Result<Boolean>
}