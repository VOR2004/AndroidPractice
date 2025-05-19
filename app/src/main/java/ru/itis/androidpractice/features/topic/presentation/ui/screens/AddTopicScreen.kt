package ru.itis.androidpractice.features.topic.presentation.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.androidpractice.features.auth.presentation.ui.uiparts.banners.NoConnectionBanner
import ru.itis.androidpractice.core.ui.uiparts.ButtonDefault
import ru.itis.androidpractice.features.topic.presentation.ui.viewmodel.AddTopicViewModel

@Composable
fun AddTopicScreen(
    viewModel: AddTopicViewModel = hiltViewModel(),
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val errorTextHeight = 16.dp

    Column(modifier = Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = state.title,
            onValueChange = { viewModel.onTitleChanged(it) },
            isError = state.titleError != null,
            label = { Text("Название обсуждения") },
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            state.titleError?.let {
                Text(text = it, color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }
        }

        OutlinedTextField(
            value = state.description,
            onValueChange = { viewModel.onDescriptionChanged(it) },
            isError = state.descriptionError != null,
            label = { Text("Описание") },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Box(modifier = Modifier.height(errorTextHeight)) {
            state.descriptionError?.let {
                Text(text = it, color = Color.Red, style = MaterialTheme.typography.labelSmall)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ButtonDefault(
            onClick = { viewModel.addTopic() },
            text = "Create topic"
        )

        if (state.showNoConnectionBanner) {
            NoConnectionBanner { viewModel.dismissNoConnectionBanner() }
        }
    }
}
