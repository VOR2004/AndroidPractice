package ru.itis.androidpractice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.itis.androidpractice.presentation.ui.screens.MainScreen
import ru.itis.androidpractice.presentation.ui.screens.RegisterScreen
import ru.itis.androidpractice.presentation.ui.screens.SignInScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Register.route) {
        composable(Routes.Register.route) {
            RegisterScreen(
                onNavigateToSignIn = {
                    navController.navigate(Routes.SignIn.route)
                },
                onNavigateToMainScreen = {
                    navController.navigate(Routes.Main.route)
                }
            )
        }
        composable(Routes.SignIn.route) {
            SignInScreen(
                onNavigateToRegister = {
                    navController.navigate(Routes.Register.route)
                },
                onNavigateToMain = {
                    navController.navigate(Routes.Main.route)
                }
            )
        }
        composable(Routes.Main.route) {
            MainScreen()
        }
    }
}