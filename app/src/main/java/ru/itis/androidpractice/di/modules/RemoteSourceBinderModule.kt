package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.domain.services.FirebaseAuthService
import ru.itis.androidpractice.data.remote.auth.FirebaseAuthServiceImpl
import ru.itis.androidpractice.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.data.remote.datasource.impl.UserRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourceBinderModule {

    @Binds
    @Singleton
    fun bindRemoteDataSource_toImpl(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    fun bindFirebaseAuthService_toImpl(impl: FirebaseAuthServiceImpl): FirebaseAuthService
}