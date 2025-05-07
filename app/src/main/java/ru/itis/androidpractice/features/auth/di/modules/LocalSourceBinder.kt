package ru.itis.androidpractice.features.auth.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.itis.androidpractice.features.auth.data.local.datasource.UserLocalDataSource
import ru.itis.androidpractice.features.auth.data.local.datasource.impl.UserLocalDataSourceImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
interface LocalSourceBinder {

    @Binds
    fun bindLocalDataSource_toImpl(impl: UserLocalDataSourceImpl): UserLocalDataSource
}