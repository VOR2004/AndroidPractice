package ru.itis.androidpractice.features.topic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.topic.data.remote.repositories.CommentRepositoryImpl
import ru.itis.androidpractice.features.topic.domain.repositories.CommentRepository

@Module
@InstallIn(SingletonComponent::class)
interface CommentBinderModule {

    @Binds
    fun bindRepository_toImpl(impl: CommentRepositoryImpl): CommentRepository
}