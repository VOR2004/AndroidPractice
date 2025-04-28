package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.data.local.datasource.impl.UserLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocalSourceBinder {
    @Binds
    @Singleton
    fun bindLocalDataSource_toImpl(impl: UserLocalDataSourceImpl): UserLocalDataSource
}