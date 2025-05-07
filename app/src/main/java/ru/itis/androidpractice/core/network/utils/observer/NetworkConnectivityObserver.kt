package ru.itis.androidpractice.core.network.utils.observer

interface NetworkConnectivityObserver {
    suspend fun isConnected(): Boolean
}