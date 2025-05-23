package ru.itis.androidpractice.features.auth.presentation.ui.uiparts.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.R

@Composable
fun Logo() {
    Image(
        painter = painterResource(R.drawable.logo_resized_512),
        contentDescription = null,
        modifier = Modifier
            .padding(top = 24.dp)
            .size(256.dp)
    )
}