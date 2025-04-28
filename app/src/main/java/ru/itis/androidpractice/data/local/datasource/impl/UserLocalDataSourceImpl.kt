package ru.itis.androidpractice.data.local.datasource.impl

import ru.itis.androidpractice.data.common.model.BaseUserModel
import ru.itis.androidpractice.data.local.dao.UserDao
import ru.itis.androidpractice.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.data.remote.mappers.UserMappers.toBaseUserModel
import ru.itis.androidpractice.data.remote.mappers.UserMappers.toUserEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao
) : UserLocalDataSource {

    override suspend fun insertUser(user: BaseUserModel) {
        userDao.insertUser(user.toUserEntity())
    }

    override suspend fun getUser(id: String): BaseUserModel? {
        return userDao.getUser(id)?.toBaseUserModel()
    }

    override suspend fun getUserByEmail(email: String): BaseUserModel? {
        return userDao.getUserByEmail(email)?.toBaseUserModel()
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return userDao.isEmailTaken(email)
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        return userDao.isUsernameTaken(username)
    }
}
