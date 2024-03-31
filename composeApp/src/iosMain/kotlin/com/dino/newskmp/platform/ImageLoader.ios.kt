package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.network.ktor.KtorNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.util.DebugLogger
import okio.FileSystem

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
        .diskCache {
            DiskCache.Builder()
                .maxSizePercent(0.25)
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "nkmp_coil_disk_cache")
                .build()
        }
        .logger(DebugLogger())
        .build()
}