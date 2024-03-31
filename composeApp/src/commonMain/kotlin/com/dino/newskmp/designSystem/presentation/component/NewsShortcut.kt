package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dino.newskmp.common.domain.model.News
import com.dino.newskmp.feature.news.presentation.screen.home.ActionImage
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_bookmark_outlined
import news_kmp.composeapp.generated.resources.ic_like_outlined
import news_kmp.composeapp.generated.resources.ic_share_outlined
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * Created by dinopriyano on 31/03/24.
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NewsShortcut(
    news: News,
    colors: CardColors,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = colors
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.End
        ) {
            NewsCard(
                news = news,
                modifier = Modifier.fillMaxWidth().weight(1f).padding(24.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                ActionImage(
                    imageResource = Res.drawable.ic_like_outlined,
                    modifier = Modifier
                ) {

                }
                ActionImage(
                    imageResource = Res.drawable.ic_bookmark_outlined,
                    modifier = Modifier
                ) {

                }
                ActionImage(
                    imageResource = Res.drawable.ic_share_outlined,
                    modifier = Modifier
                ) {

                }
            }
        }
    }
}