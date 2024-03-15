package com.dino.newskmp.feature.discovery.presentation.screen.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.dino.newskmp.common.presentation.base.BaseScreen

/**
 * Created by dinopriyano on 05/11/23.
 */

class SearchScreen :
    BaseScreen<SearchScreenModel, SearchScreenUiState, SearchScreenUiEffect, SearchScreenInteractionListener>() {

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    @Composable
    override fun onRender(state: SearchScreenUiState, listener: SearchScreenInteractionListener) {
        Text(modifier = Modifier.fillMaxSize(), text = "Search Screen")
    }

    override fun onEffect(effect: SearchScreenUiEffect, navigator: Navigator) {
        // TODO: Not yet implemented
    }
}