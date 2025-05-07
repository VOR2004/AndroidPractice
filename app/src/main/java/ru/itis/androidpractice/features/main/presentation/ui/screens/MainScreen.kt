package ru.itis.androidpractice.features.main.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.itis.androidpractice.core.ui.navigation.Routes
import ru.itis.androidpractice.core.ui.uiparts.BottomNavigationBar

@Composable
fun MainScreen(
    onNavigateTo: (Routes) -> Unit,
    currentRoute: Routes,
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onItemClick = onNavigateTo,
                currentRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {

        }
    }
}