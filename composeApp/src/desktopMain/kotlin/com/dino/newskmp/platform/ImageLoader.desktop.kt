package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import coil3.PlatformContext
import coil3.request.ImageRequest

/**
 * Created by dinopriyano on 29/03/24.
 */
internal actual val platformContext: PlatformContext
    @Composable get() = PlatformContext.INSTANCE

@Composable
internal actual fun buildImageRequest(
    data: Any?,
    requestListener: ImageRequest.Listener?
): ImageRequest {
    return ImageRequest.Builder(platformContext)
        .data(data)
        .listener(requestListener)
        .build()
}