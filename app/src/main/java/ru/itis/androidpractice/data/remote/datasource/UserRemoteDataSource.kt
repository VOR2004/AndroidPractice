package ru.itis.androidpractice.data.remote.datasource

import ru.itis.androidpractice.data.local.entities.UserEntity

interface UserRemoteDataSource {
    suspend fun insertUser(user: UserEntity)
    suspend fun getUser(id: String): UserEntity?
    suspend fun getUserByEmail(email: String): UserEntity?
    suspend fun isEmailTaken(email: String): Boolean
    suspend fun isUsernameTaken(username: String): Boolean
}