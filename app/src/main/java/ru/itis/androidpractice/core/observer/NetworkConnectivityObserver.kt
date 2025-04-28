package ru.itis.androidpractice.core.observer

interface NetworkConnectivityObserver {
    suspend fun isConnected(): Boolean
}