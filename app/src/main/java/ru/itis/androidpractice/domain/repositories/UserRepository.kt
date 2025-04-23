package ru.itis.androidpractice.domain.repositories

import ru.itis.androidpractice.data.local.db.entities.UserEntity

interface UserRepository {
    suspend fun saveUser(user: UserEntity)
    suspend fun getUser(id: String): UserEntity?
    suspend fun getUserByEmail(email: String): UserEntity?
    suspend fun isEmailTaken(email: String): Boolean
    suspend fun isUsernameTaken(username: String): Boolean
}