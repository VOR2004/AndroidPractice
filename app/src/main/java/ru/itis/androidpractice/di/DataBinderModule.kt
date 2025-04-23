package ru.itis.androidpractice.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.data.local.db.repositories.impl.UserRepositoryImpl
import ru.itis.androidpractice.domain.repositories.UserRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataBinderModule {
    @Binds
    @Singleton
    fun bindUserRepository_toImpl(impl: UserRepositoryImpl): UserRepository
}