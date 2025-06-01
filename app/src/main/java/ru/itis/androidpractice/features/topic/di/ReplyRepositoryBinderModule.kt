package ru.itis.androidpractice.features.topic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.topic.data.remote.repositories.ReplyRepositoryImpl
import ru.itis.androidpractice.features.topic.domain.repositories.ReplyRepository

@Module
@InstallIn(SingletonComponent::class)
interface ReplyRepositoryBinderModule {

    @Binds
    fun bindRepository_toImpl(impl: ReplyRepositoryImpl): ReplyRepository
}