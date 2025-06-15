package ru.itis.androidpractice.features.topic.presentation.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.itis.androidpractice.features.auth.presentation.ui.uiparts.banners.NoConnectionBanner
import ru.itis.androidpractice.core.ui.uiparts.ButtonDefault
import ru.itis.androidpractice.features.topic.presentation.ui.viewmodel.AddTopicViewModel
import ru.itis.androidpractice.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Spellcheck
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopicScreen(
    viewModel: AddTopicViewModel = hiltViewModel(),
    navigateToTopic: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val errorTextHeight = 16.dp

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.create_topic)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (state.showNoConnectionBanner) {
            NoConnectionBanner { viewModel.dismissNoConnectionBanner() }
        }

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(40.dp))

            TextField(
                value = state.title,
                onValueChange = { viewModel.onTitleChanged(it) },
                isError = state.titleError != null,
                label = { Text(stringResource(R.string.topic_title)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                singleLine = true,
            )
            Box(
                modifier = Modifier
                    .height(errorTextHeight)
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                state.titleError?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.labelSmall)
                }
            }

            OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onDescriptionChanged(it) },
                isError = state.descriptionError != null,
                label = { Text(stringResource(R.string.topic_description)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .scrollable(
                        orientation = Orientation.Horizontal,
                        state = rememberScrollState()
                    ),
            )
            Box(
                modifier = Modifier
                    .height(errorTextHeight)
                    .padding(start = 24.dp, end = 24.dp)
            ) {
                state.descriptionError?.let {
                    Text(text = it, color = Color.Red, style = MaterialTheme.typography.labelSmall)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ButtonDefault(
                    onClick = {
                        viewModel.addTopic { topicId ->
                            navigateToTopic(topicId)
                        }
                    },
                    text = stringResource(R.string.create_topic),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = { viewModel.checkGrammar() },
                    modifier = Modifier
                        .height(48.dp)
                        .width(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Spellcheck,
                        contentDescription = stringResource(R.string.check_spelling),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            if (state.grammarErrors.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .widthIn(min = 100.dp, max = 360.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 24.dp)
                        .heightIn(max = 150.dp)
                ) {
                    items(state.grammarErrors) { errorText ->
                        Text(
                            text = errorText,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}



