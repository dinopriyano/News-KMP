package com.dino.newskmp.feature.interest.presentation.screen.manage_interest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.dino.newskmp.common.presentation.base.BaseScreen
import com.dino.newskmp.designSystem.presentation.theme.PinkPastel

/**
 * Created by dinopriyano on 04/04/24.
 */

class ManageInterestScreen :
    BaseScreen<ManageInterestScreenModel, ManageInterestScreenUiState, ManageInterestScreenUiEffect, ManageInterestScreenInteractionListener>() {

    override fun onEffect(effect: ManageInterestScreenUiEffect, navigator: Navigator) {
        // TODO: Not yet implemented
    }

    @Composable
    override fun Content() {
        initScreen(getScreenModel())
    }

    @Composable
    override fun onRender(
        state: ManageInterestScreenUiState,
        listener: ManageInterestScreenInteractionListener
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = PinkPastel,
            topBar = {

            }
        ) {

        }
    }

}