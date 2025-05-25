package ru.itis.androidpractice.features.topic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.features.topic.domain.repositories.TopicRepository
import ru.itis.androidpractice.features.topic.data.remote.repositories.TopicRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface TopicRepositoryBinderModule {

    @Binds
    fun bindRepository_toImpl(impl: TopicRepositoryImpl): TopicRepository
}