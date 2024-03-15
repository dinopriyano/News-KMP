package com.dino.newskmp.common.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.dino.newskmp.designSystem.presentation.theme.BluePastel
import com.dino.newskmp.designSystem.presentation.theme.PinkPastel
import com.dino.newskmp.designSystem.presentation.theme.PurplePastel
import com.dino.newskmp.designSystem.presentation.theme.YellowPastel
import com.dino.newskmp.platform.DateTime
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.just_now_txt
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

/**
 * Created by dinopriyano on 12/11/23.
 */

fun getCardColor(currentIndex: Int): Color {
    return when (currentIndex % 4) {
        0 -> YellowPastel
        1 -> PinkPastel
        2 -> BluePastel
        else -> PurplePastel
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun formatToTimeAgo(isoDate: String): String {
    val dateTime = DateTime()
    val now = Clock.System.now()
    val then = Instant.parse(isoDate)
    val duration = now - then

    val seconds = duration.inWholeSeconds
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val months = days / 30

    return when {
        months > 0 -> dateTime.getFormattedDate(isoDate, "dd MMMM yyyy")
//        weeks > 0 -> "$weeks ${stringResource(Res.string.template_week_ago, weeks.toInt())}"
//        days > 0 -> "$days ${stringResource(Res.string.template_day_ago, days.toInt())}"
//        hours > 0 -> "$hours ${stringResource(Res.string.template_hour_ago, hours.toInt())}"
//        minutes > 0 -> "$minutes ${stringResource(Res.string.template_minute_ago, minutes.toInt())}"
        else -> stringResource(Res.string.just_now_txt)
    }
}

@Composable
fun Dp.toPx(): Float = with(LocalDensity.current) { toPx() }