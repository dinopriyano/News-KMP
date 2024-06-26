package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dino.newskmp.common.domain.model.News
import com.dino.newskmp.common.utils.formatToTimeAgo
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.unknown_txt
import news_kmp.composeapp.generated.resources.updated_at_txt
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

/**
 * Created by dinopriyano on 12/11/23.
 */

@OptIn(ExperimentalResourceApi::class)
@Composable fun NewsCard(
    news: News,
    modifier: Modifier
) {

    Column(modifier = modifier) {
        Text(
            text = news.title.orEmpty(),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "${stringResource(Res.string.updated_at_txt)} ${formatToTimeAgo(news.publishedAt.orEmpty())}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 16.dp)
        )
        AuthorView(
            authorName = news.author.orEmpty().ifEmpty { stringResource(Res.string.unknown_txt) },
            modifier = Modifier.padding(top = 16.dp).fillMaxWidth()
        )
        Text(
            text = news.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}