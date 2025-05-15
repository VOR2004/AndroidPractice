package ru.itis.androidpractice.core.session.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.core.session.data.services.FirebaseAuthServiceImpl
import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService

@Module
@InstallIn(ActivityRetainedComponent::class)
interface AuthServiceBinderModule {

    @Binds
    fun bindFirebaseAuthService_toImpl(impl: FirebaseAuthServiceImpl): FirebaseAuthService
}