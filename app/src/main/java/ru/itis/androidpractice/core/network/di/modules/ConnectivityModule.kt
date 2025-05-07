package ru.itis.androidpractice.core.network.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.itis.androidpractice.core.network.utils.observer.NetworkConnectivityObserver
import ru.itis.androidpractice.core.network.utils.observer.impl.NetworkConnectivityObserverImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ConnectivityModule {

    @Binds
    @Singleton
    fun bindConnectivityObserver(impl: NetworkConnectivityObserverImpl): NetworkConnectivityObserver
}