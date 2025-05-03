package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.core.observer.NetworkConnectivityObserver
import ru.itis.androidpractice.core.observer.impl.NetworkConnectivityObserverImpl
import ru.itis.androidpractice.core.validators.DefaultEmailValidator
import ru.itis.androidpractice.core.validators.DefaultNicknameValidator
import ru.itis.androidpractice.core.validators.DefaultPasswordValidator
import ru.itis.androidpractice.domain.validation.EmailValidator
import ru.itis.androidpractice.domain.validation.PasswordValidator
import ru.itis.androidpractice.domain.validation.UsernameValidator

@Module
@InstallIn(SingletonComponent::class)
interface UtilityBinderModule {

    @Binds
    fun bindEmailValidator_toImpl(impl: DefaultEmailValidator): EmailValidator

    @Binds
    fun bindPasswordValidator_toImpl(impl: DefaultPasswordValidator): PasswordValidator

    @Binds
    fun bindUsernameValidator_toImpl(impl: DefaultNicknameValidator): UsernameValidator

    @Binds
    fun bindConnectivityObserver_toImpl(impl: NetworkConnectivityObserverImpl): NetworkConnectivityObserver
}