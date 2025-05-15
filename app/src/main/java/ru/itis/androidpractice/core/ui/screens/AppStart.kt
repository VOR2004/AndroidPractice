package ru.itis.androidpractice.core.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.itis.androidpractice.core.navigation.SetupNavGraph
import ru.itis.androidpractice.features.auth.presentation.ui.screens.LoadingScreen
import ru.itis.androidpractice.core.session.presentaion.ui.viewmodel.SessionViewModel


@Composable
fun AppStart(sessionViewModel: SessionViewModel = hiltViewModel()) {
    val authState by sessionViewModel.authState.collectAsState()
    val isLoading by sessionViewModel.isLoading.collectAsState()
    val navController = rememberNavController()

    Crossfade(
        targetState = isLoading,
        animationSpec = tween(durationMillis = 1200),
    ) { loading ->
        when(loading) {
            true -> LoadingScreen()
            false -> SetupNavGraph(
                navController = navController,
                isSignedIn = authState
            )
        }
    }
}
