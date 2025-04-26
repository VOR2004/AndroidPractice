package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.data.repositories.impl.UserRepositoryImpl
import ru.itis.androidpractice.data.repositories.impl.UserSessionRepositoryImpl
import ru.itis.androidpractice.domain.repositories.UserRepository
import ru.itis.androidpractice.domain.repositories.UserSessionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBinderModule {

    @Binds
    @Singleton
    fun bindUserRepository_toImpl(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindUserSessionRepository_toImpl(impl: UserSessionRepositoryImpl): UserSessionRepository
}