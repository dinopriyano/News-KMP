package com.dino.newskmp.feature.main.presentation.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.dino.newskmp.feature.news.presentation.screen.home.HomeScreen

/**
 * Created by dinopriyano on 15/03/24.
 */

object HomeTab : Tab {

    @Composable
    override fun Content() {
        Navigator(screen = HomeScreen()) {
            SlideTransition(it)
        }
    }

    override val options: TabOptions
        @Composable
        get() {
            return remember { TabOptions(index = 0u, title = "Home") }
        }
}