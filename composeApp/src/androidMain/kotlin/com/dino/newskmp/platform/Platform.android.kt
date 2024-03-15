package com.dino.newskmp.platform

import com.dino.newskmp.common.presentation.config.Platform
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module
import java.util.Locale

/**
 * Created by dinopriyano on 04/11/23.
 */
actual val ioDispatcher: CoroutineDispatcher
    get() = Dispatchers.IO

actual val currentDeviceLanguage: String
    get() = Locale.getDefault().language

actual val currentPlatform: Platform
    get() = Platform.ANDROID

actual fun platformNetworkEngineModule(): Module =
    module { single { Android.create() } }