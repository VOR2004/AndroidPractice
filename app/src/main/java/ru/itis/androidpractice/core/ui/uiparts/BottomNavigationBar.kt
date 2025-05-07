package ru.itis.androidpractice.core.ui.uiparts

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.itis.androidpractice.core.ui.navigation.Routes

@Composable
fun BottomNavigationBar(
    onItemClick: (Routes) -> Unit,
    currentRoute: Routes
) {
    NavigationBar {
        bottomBarItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        onItemClick(item.route)
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
