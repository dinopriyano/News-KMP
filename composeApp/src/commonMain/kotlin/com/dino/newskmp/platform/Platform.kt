package com.dino.newskmp.platform

import com.dino.newskmp.common.presentation.config.Platform
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module

/**
 * Created by dinopriyano on 04/11/23.
 */

expect val ioDispatcher: CoroutineDispatcher

expect val currentDeviceLanguage: String

expect val currentPlatform: Platform

expect fun platformNetworkEngineModule(): Module