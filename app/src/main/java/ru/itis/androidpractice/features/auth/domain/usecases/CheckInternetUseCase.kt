package ru.itis.androidpractice.features.auth.domain.usecases

import ru.itis.androidpractice.core.network.utils.observer.NetworkConnectivityObserver
import javax.inject.Inject

class CheckInternetUseCase @Inject constructor(
    private val networkConnectivityObserver: NetworkConnectivityObserver
) {
    suspend operator fun invoke(): Boolean {
        return networkConnectivityObserver.isConnected()
    }
}