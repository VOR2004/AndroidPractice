package ru.itis.androidpractice.data.local.datasource.impl

import ru.itis.androidpractice.data.local.dao.UserDao
import ru.itis.androidpractice.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.data.local.entities.UserEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    override suspend fun getUser(id: String): UserEntity? {
        return userDao.getUser(id)
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return userDao.getUserByEmail(email)
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return userDao.isEmailTaken(email)
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        return userDao.isUsernameTaken(username)
    }
}
