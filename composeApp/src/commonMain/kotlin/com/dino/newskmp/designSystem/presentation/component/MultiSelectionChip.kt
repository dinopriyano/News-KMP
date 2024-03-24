package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastFold
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

enum class SelectionOption {
    Option, Background
}

data class RawrMultiSelectionColor(
    val containerColor: Color,
    val selectedOptionColor: Color,
    val unselectedOptionColor: Color,
    val indicatorColor: Color
)

@Composable
fun RawrMultiSelectionChip(
    options: List<String>,
    selectedIndex: Int,
    colors: RawrMultiSelectionColor,
    onOptionSelected: (String, Int) -> Unit,
    horizontalContentPadding: Dp,
    modifier: Modifier,
    scrollState: ScrollState
) {
    require(options.size >= 2) { "This composable requires at least 2 options" }
    require(options.size > selectedIndex) { "Invalid selected index" }
    RawrMultiSelectionChipImpl(
        selectedIndex, horizontalContentPadding, modifier,
        optionContent = @Composable {
            options.forEachIndexed { index, option ->
                val isSelected = selectedIndex == index
                RawrChip(
                    text = option,
                    type = ChipType.GHOST,
                    textColor = if (isSelected) colors.selectedOptionColor else colors.unselectedOptionColor,
                    containerColor = colors.containerColor,
                    modifier = Modifier.zIndex(1f),
                ) { title ->
                    onOptionSelected(title, index)
                }
            }
        },
        indicatorContent = @Composable { optionPositions ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .optionIndicatorOffset(optionPositions[selectedIndex])
                    .clip(RoundedCornerShape(50))
                    .background(colors.indicatorColor),
            )
        },
        scrollState = scrollState
    )
}

@Composable
private fun RawrMultiSelectionChipImpl(
    selectedIndex: Int,
    horizontalContentPadding: Dp,
    modifier: Modifier,
    optionContent: @Composable () -> Unit,
    indicatorContent: @Composable (optionPositions: List<OptionPosition>) -> Unit,
    scrollState: ScrollState
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollableOptionData = remember(scrollState, coroutineScope) {
        ScrollableOptionData(
            scrollState = scrollState,
            coroutineScope = coroutineScope
        )
    }
    SubcomposeLayout(
        modifier = modifier
            .horizontalScroll(scrollState)
            .selectableGroup()
            .clipToBounds()
    ) { constraints ->
        val horizontalPadding = horizontalContentPadding.roundToPx()
        val minOptionWidth = MinimumOptionWidth.roundToPx()
        val optionMeasurables = subcompose(SelectionOption.Option, optionContent)
        val layoutHeight = optionMeasurables.fastFold(0) { curr, measurable ->
            maxOf(curr, measurable.maxIntrinsicHeight(Constraints.Infinity))
        }

        val optionConstraints = constraints.copy(
            minWidth = minOptionWidth,
            minHeight = layoutHeight,
            maxHeight = layoutHeight
        )

        val optionPlaceables = mutableListOf<Placeable>()
        val optionContentWidths = mutableListOf<Dp>()
        optionMeasurables.fastForEach { measurable ->
            val placeable = measurable.measure(optionConstraints)
            val contentWidth = minOf(
                measurable.maxIntrinsicWidth(placeable.height),
                placeable.width
            ).toDp()
            optionPlaceables.add(placeable)
            optionContentWidths.add(contentWidth)
        }

        val layoutWidth = optionPlaceables.fastFold(horizontalPadding * 2) { curr, measurable ->
            curr + measurable.width
        }

        layout(layoutWidth, layoutHeight) {
            val optionPositions = mutableListOf<OptionPosition>()
            var left = horizontalPadding
            optionPlaceables.fastForEachIndexed { index, placeable ->
                placeable.placeRelative(left, 0)
                optionPositions.add(
                    OptionPosition(
                        left.toDp(),
                        placeable.width.toDp(),
                        optionContentWidths[index]
                    )
                )
                left += placeable.width
            }
            
            subcompose(SelectionOption.Background) {
                indicatorContent(optionPositions)
            }.fastForEach {
                it.measure(Constraints.fixed(layoutWidth, layoutHeight)).placeRelative(0, 0)
            }
            
            scrollableOptionData.onLaidOut(
                this@SubcomposeLayout,
                horizontalPadding,
                optionPositions,
                selectedIndex
            )
        }
    }
}

private class ScrollableOptionData(
    private val scrollState: ScrollState,
    private val coroutineScope: CoroutineScope
) {
    private var selectedOption: Int? = null

    fun onLaidOut(
        density: Density,
        edgeOffset: Int,
        optionPositions: List<OptionPosition>,
        selectedOption: Int
    ) {
        if (this.selectedOption != selectedOption) {
            this.selectedOption = selectedOption
            optionPositions.getOrNull(selectedOption)?.let {
                val calculatedOffset = it.calculateOptionOffset(density, edgeOffset, optionPositions)
                if (scrollState.value != calculatedOffset) {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(
                            calculatedOffset,
                            animationSpec = ScrollableTabRowScrollSpec
                        )
                    }
                }
            }
        }
    }

    private fun OptionPosition.calculateOptionOffset(
        density: Density,
        edgeOffset: Int,
        tabPositions: List<OptionPosition>
    ): Int = with(density) {
        val totalOptionRowWidth = (tabPositions.last().left + tabPositions.last().width).roundToPx() + edgeOffset
        val visibleWidth = totalOptionRowWidth - scrollState.maxValue
        val optionOffset = left.roundToPx()
        val scrollerCenter = visibleWidth / 2
        val optionWidth = width.roundToPx()
        val centeredOptionOffset = optionOffset - (scrollerCenter - optionWidth / 2)
        val availableSpace = (totalOptionRowWidth - visibleWidth).coerceAtLeast(0)
        return centeredOptionOffset.coerceIn(0, availableSpace)
    }
}

@Immutable
data class OptionPosition(
    val left: Dp,
    val width: Dp,
    val contentWidth: Dp
)

private val MinimumOptionWidth = 50.dp

private val ScrollableTabRowScrollSpec: AnimationSpec<Float> = tween(
    durationMillis = 250,
    easing = FastOutSlowInEasing
)

fun Modifier.optionIndicatorOffset(
    currentOptionPosition: OptionPosition
): Modifier = composed {
    val currentTabWidth by animateDpAsState(
        targetValue = currentOptionPosition.width,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = currentOptionPosition.left,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    wrapContentWidth(Alignment.Start)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}