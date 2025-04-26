package ru.itis.androidpractice.presentation.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import ru.itis.androidpractice.presentation.navigation.SetupNavGraph
import ru.itis.androidpractice.presentation.ui.viewmodel.SessionViewModel

@Composable
fun AppStart(sessionViewModel: SessionViewModel = hiltViewModel()) {
    val isSignedIn by sessionViewModel.isSignedIn.collectAsState()
    val isLoading by sessionViewModel.isLoading.collectAsState()
    val navController = rememberNavController()

    Crossfade(
        targetState = isLoading,
        animationSpec = tween(durationMillis = 1200),
        label = "AppLoadingCrossfade"
    ) { loading ->
        when(loading) {
            true -> LoadingScreen()
            false -> SetupNavGraph(
                navController = navController,
                isSignedIn = isSignedIn,
                onSignedIn = { sessionViewModel.setSignedIn(true) }
            )
        }
    }
}
