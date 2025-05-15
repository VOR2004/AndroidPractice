package ru.itis.androidpractice.features.profile.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.androidpractice.core.session.presentaion.ui.viewmodel.SessionViewModel
import ru.itis.androidpractice.core.navigation.Routes
import ru.itis.androidpractice.core.ui.uiparts.BottomNavigationBar
import ru.itis.androidpractice.R

@Composable
fun ProfileScreen(
    sessionViewModel: SessionViewModel = hiltViewModel(),
    onNavigateTo: (Routes) -> Unit,
    currentRoute: Routes
) {
    val userName by sessionViewModel.userName.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onItemClick = onNavigateTo,
                currentRoute = currentRoute
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth()
        ) {
            IconButton(
                onClick = { sessionViewModel.signOut() },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(32.dp)
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.Logout,
                    stringResource(R.string.description_logout)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = userName,
                    fontSize = 32.sp,
                )
            }
        }
    }
}