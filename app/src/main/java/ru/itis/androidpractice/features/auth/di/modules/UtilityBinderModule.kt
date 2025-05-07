package ru.itis.androidpractice.features.auth.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.auth.common.validators.DefaultEmailValidator
import ru.itis.androidpractice.features.auth.common.validators.DefaultNicknameValidator
import ru.itis.androidpractice.features.auth.common.validators.DefaultPasswordValidator
import ru.itis.androidpractice.features.auth.domain.validation.EmailValidator
import ru.itis.androidpractice.features.auth.domain.validation.PasswordValidator
import ru.itis.androidpractice.features.auth.domain.validation.UsernameValidator

@Module
@InstallIn(SingletonComponent::class)
interface UtilityBinderModule {

    @Binds
    fun bindEmailValidator_toImpl(impl: DefaultEmailValidator): EmailValidator

    @Binds
    fun bindPasswordValidator_toImpl(impl: DefaultPasswordValidator): PasswordValidator

    @Binds
    fun bindUsernameValidator_toImpl(impl: DefaultNicknameValidator): UsernameValidator
}