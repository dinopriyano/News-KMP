package com.dino.newskmp.common.data.remote

import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource

sealed class Resource<out T> {
    data object Idle : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data class Success<T>(val data: T) : Resource<T>()

    @OptIn(ExperimentalResourceApi::class)
    data class Error(
        val throwable: Throwable? = null,
        val errorMessage: String? = null,
        val localizeMessage: StringResource? = null
    ) : Resource<Nothing>()
}