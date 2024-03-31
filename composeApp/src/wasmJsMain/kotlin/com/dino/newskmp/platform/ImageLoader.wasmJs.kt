package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.network.ktor.KtorNetworkFetcherFactory
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.util.DebugLogger

/**
 * Created by dinopriyano on 29/03/24.
 */

internal actual val platformContext: PlatformContext
    @Composable get() = PlatformContext.INSTANCE

@Composable
internal actual fun getImageRequest(
    data: Any?,
    requestListener: ImageRequest.Listener?
): ImageRequest {
    return ImageRequest.Builder(platformContext)
        .data(data)
        .listener(requestListener)
        .build()
}

@Composable
internal actual fun getImageLoader(): ImageLoader {
    return ImageLoader.Builder(platformContext)
        .components {
            add(KtorNetworkFetcherFactory())
        }
        .diskCachePolicy(CachePolicy.DISABLED)
        .logger(DebugLogger())
        .build()
}