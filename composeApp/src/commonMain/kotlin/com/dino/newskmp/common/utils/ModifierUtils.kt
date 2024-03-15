package com.dino.newskmp.common.utils

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlin.math.absoluteValue

/**
 * Created by dinopriyano on 12/11/23.
 */

@OptIn(ExperimentalFoundationApi::class) fun Modifier.carouselTransition(
    page: Int,
    pagerState: PagerState
) = composed {
    val isLastPage = page == pagerState.pageCount - 1
    val pageOffset =
        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
    val absPageOffset = pageOffset.absoluteValue

    graphicsLayer {

        // Scale item
        scaleY = MathUtils.lerp(
            start = 0.9f,
            stop = 1f,
            fraction = 1f - absPageOffset.coerceIn(0f, 1f)
        )

        // Rotate item
        val maxRotationDegree = 10f
        val rotationDegree = if (isLastPage && pagerState.currentPage == pagerState.pageCount - 1) {
            0f
        } else {
            maxRotationDegree * (if (pageOffset > 0) -1 else 1) * absPageOffset.coerceIn(0f, 1f)
        }
        rotationZ = rotationDegree

        // Translation
        translationX = pageOffset * (size.width / 1.2F)
        translationY = (if (pageOffset > 0) 1 else -1) * (pageOffset * ((MathUtils.calculateRotatedHeight(size.width, size.height, rotationDegree) - size.height) / 2))

    }.zIndex(1f - absPageOffset)
}

@Composable fun Modifier.drawCircleIndicator(
    xOffset: Float,
    indicatorSize: Dp = 56.dp,
    indicatorColor: Color = MaterialTheme.colorScheme.onBackground
): Modifier = then(
    Modifier.drawBehind {
        drawCircle(
            color = indicatorColor,
            center = Offset(xOffset, indicatorSize.toPx() / 2),
            radius = indicatorSize.toPx() / 2
        )
    }
)

fun Modifier.noRippleClickable(
    onClick: () -> Unit
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    clickable(
        indication = null,
        interactionSource = interactionSource
    ) {
        onClick()
    }
}

fun Modifier.animateScaled(enabled: Boolean = true): Modifier = composed {
    var selected by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (selected) 0.8f else 1f, label = "")

    this.let {
        if(enabled) {
            this.scale(scale)
        .pointerInput(selected) {
            awaitPointerEventScope {
                selected = if (selected) {
                    waitForUpOrCancellation()
                    false
                } else {
                    awaitFirstDown(false)
                    true
                }
            }
        }
        } else {
            this
        }
    }
}
