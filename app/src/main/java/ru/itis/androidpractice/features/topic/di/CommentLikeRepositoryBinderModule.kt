package ru.itis.androidpractice.features.topic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.topic.data.remote.repositories.CommentLikeRepositoryImpl
import ru.itis.androidpractice.features.topic.domain.repositories.CommentLikeRepository

@Module
@InstallIn(SingletonComponent::class)
interface CommentLikeRepositoryBinderModule {

    @Binds
    fun bindRepository_toImpl(impl: CommentLikeRepositoryImpl): CommentLikeRepository
}