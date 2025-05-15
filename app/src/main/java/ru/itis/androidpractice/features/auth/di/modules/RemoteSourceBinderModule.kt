package ru.itis.androidpractice.features.auth.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.core.user.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.core.user.data.remote.datasource.impl.UserRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
interface RemoteSourceBinderModule {

    @Binds
    fun bindRemoteDataSource_toImpl(impl: UserRemoteDataSourceImpl): UserRemoteDataSource
}