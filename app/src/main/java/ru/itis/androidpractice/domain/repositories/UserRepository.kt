package ru.itis.androidpractice.domain.repositories

import ru.itis.androidpractice.data.common.model.BaseUserModel

interface UserRepository {
    suspend fun saveUser(user: BaseUserModel)
    suspend fun getUser(id: String): BaseUserModel?
    suspend fun getUserByEmail(email: String): BaseUserModel?
    suspend fun isEmailTaken(email: String): Boolean
    suspend fun isUsernameTaken(username: String): Boolean
}
