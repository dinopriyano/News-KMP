package com.dino.newskmp.common.data.remote

import com.dino.newskmp.common.domain.model.Wrapper
import com.dino.newskmp.platform.ioDispatcher
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.withContext
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.general_error_msg
import news_kmp.composeapp.generated.resources.no_internet_error_msg
import org.jetbrains.compose.resources.ExperimentalResourceApi

interface SafeApiCall {
    @OptIn(ExperimentalResourceApi::class)
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Wrapper<T>
    ): Resource<Wrapper<T>> {
        return withContext(ioDispatcher) {
            try {
                val result = apiCall.invoke()
                if (result.status?.lowercase() == HttpStatusCode.OK.description.lowercase()) {
                    Resource.Success(result)
                } else {
                    Resource.Error(
                        errorMessage = result.message
                    )
                }
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> {
                        Resource.Error(throwable, throwable.message, Res.string.no_internet_error_msg)
                    }
                    else -> {
                        Resource.Error(throwable, throwable.message, Res.string.general_error_msg)
                    }
                }
            }
        }
    }
}