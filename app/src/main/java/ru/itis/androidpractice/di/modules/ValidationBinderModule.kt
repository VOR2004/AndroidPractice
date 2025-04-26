package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.core.utils.DefaultEmailValidator
import ru.itis.androidpractice.core.utils.DefaultNicknameValidator
import ru.itis.androidpractice.core.utils.DefaultPasswordValidator
import ru.itis.androidpractice.domain.validation.EmailValidator
import ru.itis.androidpractice.domain.validation.PasswordValidator
import ru.itis.androidpractice.domain.validation.UsernameValidator

@Module
@InstallIn(SingletonComponent::class)
interface ValidationBinderModule {

    @Binds
    fun bindEmailValidator(impl: DefaultEmailValidator): EmailValidator

    @Binds
    fun bindPasswordValidator(impl: DefaultPasswordValidator): PasswordValidator

    @Binds
    fun bindUsernameValidator(impl: DefaultNicknameValidator): UsernameValidator
}