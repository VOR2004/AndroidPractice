package ru.itis.androidpractice.features.auth.data.local.dbinstance

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.androidpractice.features.auth.data.local.dao.UserDao
import ru.itis.androidpractice.features.auth.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = false
)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }