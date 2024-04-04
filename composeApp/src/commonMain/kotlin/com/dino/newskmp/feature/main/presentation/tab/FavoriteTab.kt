package com.dino.newskmp.feature.main.presentation.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.dino.newskmp.feature.bookmark.presentation.screen.bookmark.FavoriteScreen

/**
 * Created by dinopriyano on 05/11/23.
 */

object FavoriteTab : Tab {

    @Composable
    override fun Content() {
        FavoriteScreen().Content()
    }

    override val options: TabOptions
        @Composable
        get() {
            return remember { TabOptions(index = 2u, title = "Bookmark") }
        }

}