package ru.itis.androidpractice.core.ui.uiparts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.R

@Composable
fun ButtonDefault(onClick: () -> Unit = {}, text: String) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonColors(
            colorResource(R.color.default_button_color),
            colorResource(R.color.default_button_color_secondary),
            colorResource(R.color.default_button_color_disabled),
            colorResource(R.color.default_button_color_secondary)
        ),
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 48.dp )
            .fillMaxWidth()
    )

    {
        Text(text = text)
    }
}