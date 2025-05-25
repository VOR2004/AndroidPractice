package ru.itis.androidpractice.features.topic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.topic.common.validators.DefaultCommentValidator
import ru.itis.androidpractice.features.topic.domain.validation.CommentValidator

@Module
@InstallIn(SingletonComponent::class)
interface CommentValidationBinderModule {

    @Binds
    fun bindCommentValidator_toImpl(impl: DefaultCommentValidator): CommentValidator
}