package ru.itis.androidpractice.core.ui.uiparts

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import ru.itis.androidpractice.core.ui.navigation.Routes

data class BottomBarItem(
    val route: Routes,
    val label: String,
    val icon: ImageVector
)

val bottomBarItems = listOf(
    BottomBarItem(Routes.Profile, "Profile", Icons.Default.Person),
    BottomBarItem(Routes.Main, "Main", Icons.Default.Home),
    BottomBarItem(Routes.People, "People", Icons.Default.People)
)