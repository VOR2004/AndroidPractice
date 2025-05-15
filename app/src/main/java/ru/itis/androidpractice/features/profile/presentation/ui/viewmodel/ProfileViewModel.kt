package ru.itis.androidpractice.features.profile.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.core.session.domain.usecases.GetNameUseCase
import ru.itis.androidpractice.features.profile.presentation.ui.screenstates.ProfileState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getNameUseCase: GetNameUseCase
):
    BaseViewModel<ProfileState>(ProfileState()) {

    init {
        viewModelScope.launch {
            viewState = viewState.copy(
                name = getNameUseCase.invoke()
            )
        }
    }

}