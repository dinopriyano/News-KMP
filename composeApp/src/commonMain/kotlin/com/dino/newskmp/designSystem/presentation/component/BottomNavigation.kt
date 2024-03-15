package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.layout.*
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.dino.newskmp.common.utils.drawCircleIndicator
import com.dino.newskmp.designSystem.presentation.theme.LightTransparent
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * Created by dinopriyano on 15/03/24.
 */

val itemSize = 56.dp

@Composable
fun RawrBottomNavigationBar(
    tabs: List<TabContainer>,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = 0.38f),
    modifier: Modifier
) {
    var xIndicatorOffset by remember { mutableStateOf(Float.NaN) }
    val xOffsetAnimated by animateFloatAsState(targetValue = xIndicatorOffset)

    Row(
        modifier
            .shadow(4.dp, RoundedCornerShape(CornerSize(50)))
            .clip(RoundedCornerShape(CornerSize(50)))
            .background(MaterialTheme.colorScheme.secondary)
            .padding(5.dp)
            .selectableGroup()
            .drawCircleIndicator(xOffsetAnimated),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    )
    {
        tabs.forEach { tab ->
            RawrTabNavigationItem(
                tabContainer = tab,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                modifier = Modifier
            ) { offsets ->
                xIndicatorOffset = offsets
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RowScope.RawrTabNavigationItem(
    tabContainer: TabContainer,
    selectedContentColor: Color,
    unselectedContentColor: Color,
    modifier: Modifier,
    onSelected: (offsets: Float) -> Unit
) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current.key == tabContainer.tab.key
    val (iconColor, icon, iconSize) = if (isSelected) {
        Triple(
            MaterialTheme.colorScheme.background,
            painterResource(tabContainer.selectedIcon),
            32.dp
        )
    } else {
        Triple(
            MaterialTheme.colorScheme.onBackground,
            painterResource(tabContainer.unSelectedIcon),
            24.dp
        )
    }
    val animatedIconSize by animateDpAsState(
        targetValue = iconSize,
        animationSpec = spring(
            stiffness = Spring.StiffnessLow,
            dampingRatio = Spring.DampingRatioHighBouncy
        )
    )
    RawrBottomNavigationItem(
        modifier = Modifier
            .size(itemSize)
            .clip(CircleShape)
            .background(LightTransparent)
            .onGloballyPositioned { layoutCoordinates ->
                if (isSelected) {
                    val parentLayoutCoordinates = layoutCoordinates.parentLayoutCoordinates!!
                    val parentPosition = parentLayoutCoordinates.positionInRoot()
                    val itemCenterX =
                        layoutCoordinates.positionInRoot().x - parentPosition.x + layoutCoordinates.size.width / 2
                    onSelected(itemCenterX)
                }
            },
        unselectedContentColor = unselectedContentColor,
        selectedContentColor = selectedContentColor,
        selected = isSelected,
        onClick = { tabNavigator.current = tabContainer.tab },
        icon = {
            Icon(
                painter = icon,
                contentDescription = tabContainer.tab.options.title,
                tint = iconColor,
                modifier = Modifier.size(animatedIconSize)
            )
        }
    )
}

@OptIn(ExperimentalResourceApi::class)
data class TabContainer(
    val tab: Tab,
    val selectedIcon: DrawableResource,
    val unSelectedIcon: DrawableResource,
)

@Composable
fun RowScope.RawrBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    selectedContentColor: Color,
    unselectedContentColor: Color
) {
    val styledLabel: @Composable (() -> Unit)? = label?.let {
        @Composable {
            val style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center)
            ProvideTextStyle(style, content = label)
        }
    }

    /**
    The color of the Ripple should always the selected color, as we want to show the color
    before the item is considered selected, and hence before the new contentColor is
    provided by BottomNavigationTransition.
     **/
    val ripple = rememberRipple(bounded = false, color = selectedContentColor)

    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = ripple
            ),
        contentAlignment = Alignment.Center
    ) {
        BottomNavigationTransition(
            selectedContentColor,
            unselectedContentColor,
            selected
        ) { progress ->
            val animationProgress = if (alwaysShowLabel) 1f else progress

            BottomNavigationItemBaselineLayout(
                icon = icon,
                label = styledLabel,
                iconPositionAnimationProgress = animationProgress
            )
        }
    }
}

private val BottomNavigationAnimationSpec = TweenSpec<Float>(
    durationMillis = 300,
    easing = FastOutSlowInEasing
)

@Composable
private fun BottomNavigationTransition(
    activeColor: Color,
    inactiveColor: Color,
    selected: Boolean,
    content: @Composable (animationProgress: Float) -> Unit
) {
    val animationProgress by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = BottomNavigationAnimationSpec
    )

    val color = lerp(inactiveColor, activeColor, animationProgress)

    CompositionLocalProvider(
        LocalContentColor provides color.copy(alpha = 1f)
    ) {
        content(animationProgress)
    }
}

@Composable
private fun BottomNavigationItemBaselineLayout(
    icon: @Composable () -> Unit,
    label: @Composable (() -> Unit)?,
    /*@FloatRange(from = 0.0, to = 1.0)*/
    iconPositionAnimationProgress: Float
) {
    Layout(
        {
            Box(Modifier.layoutId("icon")) { icon() }
            if (label != null) {
                Box(
                    Modifier
                        .layoutId("label")
                        .alpha(iconPositionAnimationProgress)
                        .padding(horizontal = BottomNavigationItemHorizontalPadding)
                ) { label() }
            }
        }
    ) { measurables, constraints ->
        val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)

        val labelPlaceable = label?.let {
            measurables.first { it.layoutId == "label" }.measure(
                // Measure with loose constraints for height as we don't want the label to take up more
                // space than it needs
                constraints.copy(minHeight = 0)
            )
        }

        // If there is no label, just place the icon.
        if (label == null) {
            placeIcon(iconPlaceable, constraints)
        } else {
            placeLabelAndIcon(
                labelPlaceable!!,
                iconPlaceable,
                constraints,
                iconPositionAnimationProgress
            )
        }
    }
}

private fun MeasureScope.placeIcon(
    iconPlaceable: Placeable,
    constraints: Constraints
): MeasureResult {
    val height = constraints.maxHeight
    val iconY = (height - iconPlaceable.height) / 2
    return layout(iconPlaceable.width, height) {
        iconPlaceable.placeRelative(0, iconY)
    }
}

private fun MeasureScope.placeLabelAndIcon(
    labelPlaceable: Placeable,
    iconPlaceable: Placeable,
    constraints: Constraints,
    /*@FloatRange(from = 0.0, to = 1.0)*/
    iconPositionAnimationProgress: Float
): MeasureResult {
    val height = constraints.maxHeight

    val firstBaseline = labelPlaceable[FirstBaseline]
    val baselineOffset = CombinedItemTextBaseline.roundToPx()
    val netBaselineAdjustment = baselineOffset - firstBaseline

    val contentHeight = iconPlaceable.height + labelPlaceable.height + netBaselineAdjustment
    val contentVerticalPadding = ((height - contentHeight) / 2).coerceAtLeast(0)

    val unselectedIconY = (height - iconPlaceable.height) / 2
    // Icon should be [contentVerticalPadding] from the top
    val selectedIconY = contentVerticalPadding

    // Label's first baseline should be [baselineOffset] below the icon
    val labelY = selectedIconY + iconPlaceable.height + netBaselineAdjustment

    val containerWidth = max(labelPlaceable.width, iconPlaceable.width)

    val labelX = (containerWidth - labelPlaceable.width) / 2
    val iconX = (containerWidth - iconPlaceable.width) / 2

    // How far the icon needs to move between unselected and selected states
    val iconDistance = unselectedIconY - selectedIconY

    // When selected the icon is above the unselected position, so we will animate moving
    // downwards from the selected state, so when progress is 1, the total distance is 0, and we
    // are at the selected state.
    val offset = (iconDistance * (1 - iconPositionAnimationProgress)).roundToInt()

    return layout(containerWidth, height) {
        if (iconPositionAnimationProgress != 0f) {
            labelPlaceable.placeRelative(labelX, labelY + offset)
        }
        iconPlaceable.placeRelative(iconX, selectedIconY + offset)
    }
}

private val BottomNavigationItemHorizontalPadding = 12.dp

private val CombinedItemTextBaseline = 12.dp