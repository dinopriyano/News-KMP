package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.request.ImageRequest

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
    // TODO: put image loader here
}