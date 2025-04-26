package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.ClearSessionUseCase
import ru.itis.androidpractice.domain.usecases.SetSignInStatusUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val clearSession: ClearSessionUseCase,
    private val setSignInStatus: SetSignInStatusUseCase
): ViewModel() {

    fun logout() {
        viewModelScope.launch {
            clearSession()
            setSignInStatus(false)
        }
    }
}