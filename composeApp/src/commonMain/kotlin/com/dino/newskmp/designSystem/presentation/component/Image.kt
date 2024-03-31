package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.disk.DiskCache
import coil3.network.ktor.KtorNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.dino.newskmp.platform.buildImageRequest
import com.dino.newskmp.platform.platformContext
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import okio.FileSystem
import org.koin.compose.koinInject

/**
 * Created by dinopriyano on 29/03/24.
 */

@Composable
fun RawrImage(
    data: Any?,
    requestListener: ImageRequest.Listener? = null,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    modifier: Modifier
) {
//    val painter = rememberAsyncImagePainter(
//        model = buildImageRequest(data, requestListener)
//    )
//    Image(
//        painter = painter,
//        contentScale = contentScale,
//        contentDescription = contentDescription,
//        modifier = modifier
//    )
    val imageRequest: ImageRequest = buildImageRequest(data, requestListener)
    val httpClient: HttpClient = koinInject()
    val imageLoader: ImageLoader = ImageLoader.Builder(platformContext)
        .components {
            add(KtorNetworkFetcherFactory(httpClient))
        }
        .diskCache {
            DiskCache.Builder()
                .maxSizePercent(0.03)
                .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "nkmp_coil_disk_cache")
                .build()
        }
        .crossfade(500)
        .logger(DebugLogger())
        .build()
    AsyncImage(
        model = imageRequest,
        imageLoader = imageLoader,
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier,
        onSuccess = {
            Napier.d("onSuccess")
        },
        onLoading = {
            Napier.d("onLoading")
        },
        onError = {
            Napier.e(it.result.throwable.message ?: "")
        }
    )
}