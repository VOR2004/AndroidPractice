package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.data.repositories.impl.UserRepositoryImpl
import ru.itis.androidpractice.domain.repositories.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryBinderModule {

    @Binds
    @Singleton
    fun bindUserRepository_toImpl(impl: UserRepositoryImpl): UserRepository
}