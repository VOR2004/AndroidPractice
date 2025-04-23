package ru.itis.androidpractice.data.local.db.repositories.impl

import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.itis.androidpractice.data.local.db.dao.UserDao
import ru.itis.androidpractice.data.local.db.entities.UserEntity
import ru.itis.androidpractice.domain.repositories.UserRepository

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
) : UserRepository {

    override suspend fun saveUser(user: UserEntity) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    override suspend fun getUser(id: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            userDao.getUser(id)
        }
    }

    override suspend fun getUserByEmail(email: String): UserEntity? {
        return withContext(Dispatchers.IO) {
            userDao.getUserByEmail(email)
        }
    }

    override suspend fun isEmailTaken(email: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.isEmailTaken(email)
        }
    }

    override suspend fun isUsernameTaken(username: String): Boolean {
        return withContext(Dispatchers.IO) {
            userDao.isUsernameTaken(username)
        }
    }
}

//return@withContext надо если несколько строк