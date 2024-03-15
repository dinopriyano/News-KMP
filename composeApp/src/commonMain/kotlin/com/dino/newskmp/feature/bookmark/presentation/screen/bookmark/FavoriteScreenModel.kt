package com.dino.newskmp.feature.bookmark.presentation.screen.bookmark

import cafe.adriel.voyager.core.model.screenModelScope
import com.dino.newskmp.common.presentation.base.BaseScreenModel
import kotlinx.coroutines.CoroutineScope

/**
 * Created by dinopriyano on 05/11/23.
 */

class FavoriteScreenModel : BaseScreenModel<FavoriteScreenUiState, FavoriteScreenUiEffect>(FavoriteScreenUiState()),
    FavoriteScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope


}