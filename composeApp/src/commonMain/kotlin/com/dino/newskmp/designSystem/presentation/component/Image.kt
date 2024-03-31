package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.dino.newskmp.platform.getImageLoader
import com.dino.newskmp.platform.getImageRequest

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
    val imageRequest: ImageRequest = getImageRequest(data, requestListener)
    val imageLoader: ImageLoader = getImageLoader()
    AsyncImage(
        model = imageRequest,
        imageLoader = imageLoader,
        contentScale = contentScale,
        contentDescription = contentDescription,
        modifier = modifier
    )
}