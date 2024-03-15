package com.dino.newskmp.platform

import androidx.compose.ui.text.intl.Locale
import com.dino.newskmp.common.presentation.config.Platform
import io.ktor.client.engine.js.Js
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Created by dinopriyano on 04/11/23.
 */
actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.Default

actual val currentDeviceLanguage: String
    get() = Locale.current.language

actual val currentPlatform: Platform
    get() = Platform.WEB

actual fun platformNetworkEngineModule(): Module =
    module { single { Js.create() } }