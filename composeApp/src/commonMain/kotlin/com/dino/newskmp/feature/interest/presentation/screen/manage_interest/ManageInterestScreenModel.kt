package com.dino.newskmp.feature.interest.presentation.screen.manage_interest

import cafe.adriel.voyager.core.model.screenModelScope
import com.dino.newskmp.common.presentation.base.BaseScreenModel
import kotlinx.coroutines.CoroutineScope

/**
 * Created by dinopriyano on 04/04/24.
 */
class ManageInterestScreenModel(

) : BaseScreenModel<ManageInterestScreenUiState, ManageInterestScreenUiEffect>(
    ManageInterestScreenUiState()
), ManageInterestScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope

}