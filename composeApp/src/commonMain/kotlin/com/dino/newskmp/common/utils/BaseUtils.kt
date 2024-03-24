package com.dino.newskmp.common.utils

import androidx.compose.runtime.Composable
import com.dino.newskmp.common.presentation.config.ENGLISH_LANGUAGE
import com.dino.newskmp.common.presentation.config.INDONESIAN_LANGUAGE
import com.dino.newskmp.platform.currentDeviceLanguage
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

fun getDefaultLanguage(): String {
    return if (currentDeviceLanguage == INDONESIAN_LANGUAGE) {
        INDONESIAN_LANGUAGE
    } else {
        ENGLISH_LANGUAGE
    }
}

fun Any?.isNull() = this == null

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getPulralStringValue(quantity: Long, resourceOne: StringResource, resourceOther: StringResource? = null): String {
    return if (resourceOther.isNull() || quantity < 2 ) {
        stringResource(resourceOne, quantity)
    } else {
        stringResource(resourceOther!!, quantity)
    }
}

//fun getFormattedDateString()
