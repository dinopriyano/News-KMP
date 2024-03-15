package com.dino.newskmp.feature.main.presentation.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import com.dino.newskmp.feature.discovery.presentation.screen.search.SearchScreen

/**
 * Created by dinopriyano on 05/11/23.
 */

object SearchTab: Tab {

  @Composable
  override fun Content() {
    Navigator(screen = SearchScreen()) {
      SlideTransition(it)
    }
  }

  override val options: TabOptions
    @Composable
    get() {
      return remember { TabOptions(index = 1u, title = "Search") }
    }
}