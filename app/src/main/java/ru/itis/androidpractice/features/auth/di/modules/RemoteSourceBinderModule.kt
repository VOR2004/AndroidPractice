package ru.itis.androidpractice.features.auth.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.features.auth.domain.services.FirebaseAuthService
import ru.itis.androidpractice.features.auth.data.remote.services.FirebaseAuthServiceImpl
import ru.itis.androidpractice.features.auth.data.remote.datasource.UserRemoteDataSource
import ru.itis.androidpractice.features.auth.data.remote.datasource.impl.UserRemoteDataSourceImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RemoteSourceBinderModule {

    @Binds
    fun bindRemoteDataSource_toImpl(impl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    fun bindFirebaseAuthService_toImpl(impl: FirebaseAuthServiceImpl): FirebaseAuthService
}