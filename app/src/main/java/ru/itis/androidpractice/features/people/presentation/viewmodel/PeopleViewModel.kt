package ru.itis.androidpractice.features.people.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.itis.androidpractice.core.ui.viewmodel.BaseViewModel
import ru.itis.androidpractice.core.user.domain.repositories.UserRepository
import ru.itis.androidpractice.core.user.utils.Statuses
import ru.itis.androidpractice.features.people.presentation.screenstates.PeopleState
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<PeopleState>(PeopleState()) {

    fun onInputUsernameChange(newInput: String) {
        viewState = viewState.copy(inputUsername = newInput, isError = false)
    }

    fun searchUser() {
        val usernameToSearch = viewState.inputUsername.trim()
        if (usernameToSearch.isEmpty()) {
            viewState = viewState.copy(isError = true)
            return
        }

        viewModelScope.launch {
            val user = userRepository.findUserByUsername(usernameToSearch)
            if (user == null) {
                viewState = viewState.copy(isError = true, username = "", rating = "", status = "")
            } else {
                val ratingInt = userRepository.getRating(user.id)
                val status = getStatusByRating(ratingInt)
                viewState = viewState.copy(
                    username = user.username,
                    rating = ratingInt.toString(),
                    status = status,
                    isError = false
                )
            }
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
}
