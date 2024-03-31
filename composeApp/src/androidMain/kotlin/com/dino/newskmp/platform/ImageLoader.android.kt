package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import coil3.PlatformContext
import coil3.request.ImageRequest
import coil3.request.lifecycle

/**
 * Created by dinopriyano on 29/03/24.
 */

internal actual val platformContext: PlatformContext
    @Composable get() = LocalContext.current

@Composable
internal actual fun buildImageRequest(
    data: Any?,
    requestListener: ImageRequest.Listener?
): ImageRequest {
    val lifecycleOwner = LocalLifecycleOwner.current
    return ImageRequest.Builder(platformContext)
        .data(data)
        .listener(requestListener)
        .lifecycle(lifecycleOwner)
        .build()
}
