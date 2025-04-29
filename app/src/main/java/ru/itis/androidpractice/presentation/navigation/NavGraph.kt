package ru.itis.androidpractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.itis.androidpractice.presentation.ui.screens.MainScreen
import ru.itis.androidpractice.presentation.ui.screens.RegisterScreen
import ru.itis.androidpractice.presentation.ui.screens.SignInScreen

@Composable
fun SetupNavGraph(navController: NavHostController, isSignedIn: Boolean, onSignedIn: () -> Unit) {

    val startDestination = if (isSignedIn) Routes.Main else Routes.SignIn

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Routes.Register> {
            RegisterScreen(
                onNavigateToSignIn = {
                    navController.navigate(Routes.SignIn) {
                        popUpTo(Routes.Register) { inclusive = true }
                    }
                }
            )
        }
        composable<Routes.SignIn> {
            SignInScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.Register) {
                        popUpTo(Routes.SignIn) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Routes.Main) {
                        popUpTo(Routes.SignIn) { inclusive = true }
                    }
                },
                onSignedIn = onSignedIn
            )
        }
        composable<Routes.Main> {
            MainScreen()
        }
    }
}
