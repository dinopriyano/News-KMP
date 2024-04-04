package com.dino.newskmp.feature.main.presentation.tab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.dino.newskmp.feature.discovery.presentation.screen.search.SearchScreen

/**
 * Created by dinopriyano on 05/11/23.
 */

object SearchTab: Tab {

  @Composable
  override fun Content() {
    SearchScreen().Content()
  }

  override val options: TabOptions
    @Composable
    get() {
      return remember { TabOptions(index = 1u, title = "Search") }
    }
}