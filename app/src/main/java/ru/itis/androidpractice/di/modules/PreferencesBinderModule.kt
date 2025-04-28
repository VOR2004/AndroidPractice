package ru.itis.androidpractice.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.data.local.preferences.impl.UserPreferencesImpl
import ru.itis.androidpractice.data.local.preferences.UserPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferencesBinderModule {

    @Binds
    @Singleton
    fun bindUserPreferences_toImpl(impl: UserPreferencesImpl): UserPreferences
}