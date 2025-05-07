package ru.itis.androidpractice.features.auth.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.features.auth.data.repositories.impl.UserRepositoryImpl
import ru.itis.androidpractice.features.auth.domain.repositories.UserRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryBinderModule {

    @Binds
    fun bindUserRepository_toImpl(impl: UserRepositoryImpl): UserRepository
}