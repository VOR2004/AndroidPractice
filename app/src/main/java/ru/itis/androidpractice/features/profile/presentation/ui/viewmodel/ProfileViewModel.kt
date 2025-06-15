package ru.itis.androidpractice.features.profile.presentation.ui.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.session.domain.usecases.GetCurrentIdUseCase
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.core.session.domain.usecases.GetNameUseCase
import ru.itis.androidpractice.core.session.domain.usecases.GetRatingUseCase
import ru.itis.androidpractice.core.user.utils.Statuses
import ru.itis.androidpractice.features.profile.domain.SignOutUseCase
import ru.itis.androidpractice.features.profile.presentation.ui.screenstates.ProfileState
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getNameUseCase: GetNameUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getRatingUseCase: GetRatingUseCase,
    private val getCurrentIdUseCase: GetCurrentIdUseCase,
) : BaseViewModel<ProfileState>(ProfileState()) {

    init {
        viewModelScope.launch {
            val name = getNameUseCase.invoke()
            val ratingStr = getRatingUseCase.invoke(getCurrentIdUseCase.invoke())
            val ratingInt = ratingStr.toIntOrNull() ?: 0
            val status = getStatusByRating(ratingInt)

            viewState = viewState.copy(
                name = name,
                rating = ratingStr,
                status = status
            )
        }
    }

    private fun getStatusByRating(rating: Int): String {
        return when {
            rating == 0 -> Statuses.STATUS_NOOB
            rating in 1..9 -> Statuses.STATUS_BEGINNER
            rating >= 50 -> Statuses.STATUS_PRO
            else -> ""
        }
    }

    fun signOut() {
        viewModelScope.launch {
            signOutUseCase.execute()
        }
    }
}