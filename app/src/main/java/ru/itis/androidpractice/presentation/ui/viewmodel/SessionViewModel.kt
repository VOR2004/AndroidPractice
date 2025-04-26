package ru.itis.androidpractice.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.itis.androidpractice.domain.usecases.GetSignInStatusUseCase
import ru.itis.androidpractice.domain.usecases.SetSignInStatusUseCase
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val getSignInStatus: GetSignInStatusUseCase,
    private val setSignInStatus: SetSignInStatusUseCase,
) : ViewModel() {

    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> get() = _isSignedIn

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    init {
        viewModelScope.launch {
            getSignInStatus().collect { signedIn ->
                _isSignedIn.value = signedIn
                _isLoading.value = false
            }
        }
    }

    fun setSignedIn(signedIn: Boolean) {
        viewModelScope.launch {
            _isLoading.value = true
            setSignInStatus(signedIn)
            _isSignedIn.value = signedIn
            _isLoading.value = false
        }
    }
}
