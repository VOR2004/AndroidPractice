package ru.itis.androidpractice.core.user.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.core.user.domain.repositories.UserRepository
import ru.itis.androidpractice.core.user.data.repositories.impl.UserRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryBinderModule {

    @Binds
    fun bindUserRepository_toImpl(impl: UserRepositoryImpl): UserRepository
}