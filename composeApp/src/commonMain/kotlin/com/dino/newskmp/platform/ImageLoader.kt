package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.request.ImageRequest

/**
 * Created by dinopriyano on 31/03/24.
 */

internal expect val platformContext: PlatformContext

@Composable
internal expect fun getImageRequest(
    data: Any?,
    requestListener: ImageRequest.Listener?
): ImageRequest

@Composable
internal expect fun getImageLoader(): ImageLoader