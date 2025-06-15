package ru.itis.androidpractice.features.main.presentation.ui.uiparts

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.itis.androidpractice.features.topic.data.remote.entities.TopicEntity

@Composable
fun TopicsPager(
    topics: List<TopicEntity>,
    onTopicClick: (TopicEntity) -> Unit,
    pageSize: Int = 10
) {
    val pages = topics.chunked(pageSize)
    val pagerState = rememberPagerState(pageCount = { pages.size })

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { pageIndex ->
        val pageTopics = pages[pageIndex]

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(pageTopics, key = { it.id }) { topic ->
                TopicItem(
                    topic = topic,
                    onClick = { onTopicClick(topic) }
                )
            }
        }
    }
}

@Composable
fun TopicItem(
    topic: TopicEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = topic.title,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = topic.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
