package com.dino.newskmp.common.presentation.base

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.dino.newskmp.platform.SystemBarColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.mp.KoinPlatform

/**
 * Created by dinopriyano on 11/11/23.
 */

@Suppress("BOUNDS_NOT_ALLOWED_IF_BOUNDED_BY_TYPE_PARAMETER")
abstract class BaseScreen<VM, S, E, I> : Screen
        where I : BaseInteractionListener, VM : BaseScreenModel<S, E>, VM : I {

    @Composable
    fun initScreen(viewModel: VM) {
        val state: S by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        setSystemBarColor()
        onRender(state, viewModel)
        Listen(viewModel.effect) {
            onEffect(effect = it, navigator = navigator)
        }
    }

    abstract fun onEffect(effect: E, navigator: Navigator)

    @Composable
    abstract fun onRender(state: S, listener: I)

    @Composable
    private fun Listen(effect: Flow<E>, function: (E) -> Unit) {
        LaunchedEffect(Unit) {
            effect.collectLatest {
                it?.let { function(it) }
            }
        }
    }

    @Composable
    inline fun <reified T : ScreenModel> getScreenModel(
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null,
    ): T {
        val koin = KoinPlatform.getKoin()
        return rememberScreenModel<T>(tag = qualifier?.value) { koin.get(qualifier, parameters) }
    }

    @Composable
    open fun setSystemBarColor() {
        SystemBarColor(
            statusBarColor = MaterialTheme.colorScheme.background,
            navigationBarColor = MaterialTheme.colorScheme.background
        )
    }
}
