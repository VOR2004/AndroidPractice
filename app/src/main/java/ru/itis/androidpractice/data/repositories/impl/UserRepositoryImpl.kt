package ru.itis.androidpractice.data.repositories.impl

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.androidpractice.data.local.dao.UserDao
import ru.itis.androidpractice.data.local.entities.UserEntity
import ru.itis.androidpractice.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.domain.repositories.UserRepository

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun saveUser(user: UserEntity) {
        withContext(Dispatchers.IO) {
            userRemoteDataSource.insertUser(user)
            userDao.insertUser(user)
        }
    }

    override suspend fun getUser(id: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            val localUser = userDao.getUser(id)
            if (localUser != null) {
                return@withContext localUser
            } else {
                val remoteUser = userRemoteDataSource.getUser(id)
                return@withContext remoteUser?.also {
                    userDao.insertUser(it)
                }
            }
        }
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            val localUser = userDao.getUserByEmail(email)
            if (localUser != null) {
                return@withContext localUser
            } else {
                val remoteUser = userRemoteDataSource.getUserByEmail(email)
                return@withContext remoteUser?.also{
                    userDao.insertUser(it)
                }
            }
        }
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            userRemoteDataSource.isEmailTaken(email)
        }
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            userRemoteDataSource.isUsernameTaken(username)
        }
    }
}