package ru.itis.androidpractice.features.auth.data.local.datasource.impl

import ru.itis.androidpractice.features.auth.domain.model.BaseUserModel
import ru.itis.androidpractice.features.auth.data.local.dao.UserDao
import ru.itis.androidpractice.features.auth.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.features.auth.data.local.mappers.UserRoomMapper
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userDao: UserDao,
    private val mapper: UserRoomMapper
) : UserLocalDataSource {

    override suspend fun insertUser(user: BaseUserModel) {
        userDao.insertUser(mapper.toUserEntity(user))
    }

    override suspend fun getUser(id: String): BaseUserModel? {
        return userDao.getUser(id)?.let(mapper::toBaseUserModel)
    }

    override suspend fun getUserByEmail(email: String): BaseUserModel? {
        return userDao.getUserByEmail(email)?.let(mapper::toBaseUserModel)
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return userDao.isEmailTaken(email)
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        return userDao.isUsernameTaken(username)
    }
}
