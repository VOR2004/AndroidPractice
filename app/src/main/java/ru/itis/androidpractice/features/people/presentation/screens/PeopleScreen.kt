package ru.itis.androidpractice.features.people.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.itis.androidpractice.core.navigation.Routes
import ru.itis.androidpractice.core.ui.uiparts.BottomNavigationBar

@Composable
fun PeopleScreen(
    onNavigateTo: (Routes) -> Unit,
    currentRoute: Routes

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