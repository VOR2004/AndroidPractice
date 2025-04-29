package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.services.FirebaseAuthService
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authService: FirebaseAuthService
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val authState: StateFlow<Boolean> = authService.observeAuthState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    init {
        viewModelScope.launch {
            authService.observeAuthState().first()
            _isLoading.value = false
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authService.signOut()
        }
    }
}
