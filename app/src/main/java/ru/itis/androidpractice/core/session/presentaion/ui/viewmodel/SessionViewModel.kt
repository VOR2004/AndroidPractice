package ru.itis.androidpractice.core.session.presentaion.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.session.domain.services.FirebaseAuthService
import ru.itis.androidpractice.core.session.domain.usecases.GetNameUseCase
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val authService: FirebaseAuthService,
    private val getNameUseCase: GetNameUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    val authState: StateFlow<Boolean> = authService.observeAuthState()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName

    init {
        viewModelScope.launch {
            authService.observeAuthState().first()
            if (authService.getCurrentUserId() != null) {
                _userName.value = getNameUseCase.invoke()
            }
            _isLoading.value = false
        }
    }

    fun reloadUserName() {
        viewModelScope.launch {
            val id = authService.getCurrentUserId()
            _userName.value = if (id != null) getNameUseCase.invoke() else ""
        }
    }

    fun signOut() {
        viewModelScope.launch {
            authService.signOut()
            _userName.value = ""
        }
    }
}
