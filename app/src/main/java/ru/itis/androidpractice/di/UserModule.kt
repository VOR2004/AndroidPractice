package ru.itis.androidpractice.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.core.utils.PasswordHasher
import javax.inject.Singleton
import ru.itis.androidpractice.data.local.db.instance.AppDatabase
import ru.itis.androidpractice.data.local.db.dao.UserDao

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun providePasswordHasher(): PasswordHasher = PasswordHasher()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao =
        appDatabase.userDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "user_database"
            ).fallbackToDestructiveMigration(false).build()
}