package ru.itis.androidpractice.data.local.db.instance

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.itis.androidpractice.data.local.db.dao.UserDao
import ru.itis.androidpractice.data.local.db.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2,
    exportSchema = false
)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }