package ru.itis.androidpractice.data.local.datasource

import ru.itis.androidpractice.data.common.model.BaseUserModel

interface UserLocalDataSource {
    suspend fun insertUser(user: BaseUserModel)
    suspend fun getUser(id: String): BaseUserModel?
    suspend fun getUserByEmail(email: String): BaseUserModel?
    suspend fun isEmailTaken(email: String): Boolean
    suspend fun isUsernameTaken(username: String): Boolean
}