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

    val startDestination = if (isSignedIn) Routes.Main.route else Routes.SignIn.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.Register.route) {
            RegisterScreen(
                onNavigateToSignIn = {
                    navController.navigate(Routes.SignIn.route) {
                        popUpTo(Routes.Register.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.SignIn.route) {
            SignInScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.Register.route) {
                        popUpTo(Routes.SignIn.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Routes.Main.route) {
                        popUpTo(Routes.SignIn.route) { inclusive = true }
                    }
                },
                onSignedIn = onSignedIn
            )
        }
        composable(Routes.Main.route) {
            MainScreen()
        }
    }
}
