package ru.itis.androidpractice.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import ru.itis.androidpractice.features.main.presentation.ui.screens.MainScreen
import ru.itis.androidpractice.features.auth.presentation.ui.screens.RegisterScreen
import ru.itis.androidpractice.features.auth.presentation.ui.screens.SignInScreen
import ru.itis.androidpractice.features.people.presentation.screens.PeopleScreen
import ru.itis.androidpractice.features.profile.presentation.ui.screens.ProfileScreen
import ru.itis.androidpractice.features.topic.presentation.ui.screens.AddTopicScreen
import ru.itis.androidpractice.features.topic.presentation.ui.screens.TopicDetailsScreen

@Composable
fun SetupNavGraph(navController: NavHostController, isSignedIn: Boolean) {

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
            )
        }

        composable<Routes.Main> {
            MainScreen(
                onNavigateTo = { route ->
                    navController.navigate(route) {
                        popUpTo(Routes.Main) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                currentRoute = Routes.Main,
                onCreateClick = {
                    navController.navigate(Routes.CreateTopic)
                }
            )
        }

        composable<Routes.Profile> {
            ProfileScreen(
                onNavigateTo = { route ->
                    navController.navigate(route) {
                        popUpTo(Routes.Profile) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                currentRoute = Routes.Profile
            )
        }

        composable<Routes.People> {
            PeopleScreen(
                onNavigateTo = { route ->
                    navController.navigate(route) {
                        popUpTo(Routes.People) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                currentRoute = Routes.People
            )
        }

        composable<Routes.CreateTopic> {
            AddTopicScreen(
                navigateToTopic = { topicId ->
                    navController.navigate(Routes.Topic(topicId))
                }
            )
        }

        composable<Routes.Topic> { backStackEntry ->
            val topic: Routes.Topic = backStackEntry.toRoute()
            TopicDetailsScreen(id = topic.topicId)
        }
    }
}
