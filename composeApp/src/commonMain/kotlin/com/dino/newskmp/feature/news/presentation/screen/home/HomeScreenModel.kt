package com.dino.newskmp.feature.news.presentation.screen.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.dino.newskmp.common.presentation.base.BaseScreenModel
import kotlinx.coroutines.CoroutineScope

/**
 * Created by dinopriyano on 05/11/23.
 */
class HomeScreenModel : BaseScreenModel<HomeScreenUiState, HomeScreenUiEffect>(HomeScreenUiState()),
    HomeScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope


}