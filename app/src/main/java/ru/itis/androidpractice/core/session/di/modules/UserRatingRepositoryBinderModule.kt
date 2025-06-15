package ru.itis.androidpractice.core.session.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.core.session.data.repositories.UserRatingRepositoryImpl
import ru.itis.androidpractice.core.session.domain.repositories.UserRatingRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
interface UserRatingRepositoryBinderModule {

    @Binds
    fun bindRepository_toImpl(impl: UserRatingRepositoryImpl): UserRatingRepository
}
