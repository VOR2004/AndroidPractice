package ru.itis.androidpractice.presentation.ui.uiparts.buttons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.R

@Composable
fun ButtonEnter(onClick: () -> Unit = {}) {
    IconButton(
        onClick = onClick
    )
    {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Enter",
            modifier = Modifier.size(20.dp),
            tint = colorResource(R.color.default_light_blue_color)
        )
    }
}