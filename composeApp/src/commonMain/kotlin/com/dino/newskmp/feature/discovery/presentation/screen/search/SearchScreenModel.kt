package com.dino.newskmp.feature.discovery.presentation.screen.search

import cafe.adriel.voyager.core.model.screenModelScope
import com.dino.newskmp.common.presentation.base.BaseScreenModel
import kotlinx.coroutines.CoroutineScope

/**
 * Created by dinopriyano on 05/11/23.
 */

class SearchScreenModel : BaseScreenModel<SearchScreenUiState, SearchScreenUiEffect>(SearchScreenUiState()),
    SearchScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope


}