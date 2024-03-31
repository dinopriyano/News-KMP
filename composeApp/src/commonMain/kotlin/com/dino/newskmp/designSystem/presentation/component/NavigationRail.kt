package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * Created by dinopriyano on 01/04/24.
 */

@Composable
fun RawrNavigationRail(
    tabs: List<TabContainer>,
    selectedContentColor: Color = LocalContentColor.current,
    unselectedContentColor: Color = selectedContentColor.copy(alpha = 0.38f),
    modifier: Modifier
) {
    NavigationRail(
        modifier = modifier,
        header = {
            Spacer(Modifier.height(56.dp))
        }
    ) {
        tabs.forEach { tab ->
            RawrNavigationRailItem(
                tabContainer = tab,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                modifier = Modifier,
                onSelected = {

                }
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun RawrNavigationRailItem(
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
    NavigationRailItem(
        selected = isSelected,
        modifier = modifier,
        colors = NavigationRailItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.onBackground
        ),
        onClick = {
            tabNavigator.current = tabContainer.tab
        },
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