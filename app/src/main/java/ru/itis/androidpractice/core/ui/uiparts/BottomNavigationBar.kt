package ru.itis.androidpractice.core.ui.uiparts

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.itis.androidpractice.core.navigation.Routes

import ru.itis.androidpractice.R
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigationBar(
    onItemClick: (Routes) -> Unit,
    currentRoute: Routes
) {
    NavigationBar(
        tonalElevation = 4.dp
    ) {
        bottomBarItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        onItemClick(item.route)
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (currentRoute == item.route) colorResource(R.color.default_button_color)
                        else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (currentRoute == item.route) colorResource(R.color.default_button_color)
                        else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(R.color.default_button_color),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = colorResource(R.color.default_button_color),
                    unselectedTextColor = Color.Gray,
                    indicatorColor = colorResource(R.color.default_button_color_third),
                )
            )
        }
    }
}

