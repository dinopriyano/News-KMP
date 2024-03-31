package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

/**
 * Created by dinopriyano on 29/03/24.
 */

@Composable
fun RawrImage(
    data: Any?,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String? = null,
    modifier: Modifier
) {
    when {
        (data is String && data.contains("http")) -> {
            val resource = asyncPainterResource(data)
            KamelImage(
                resource = resource,
                modifier = modifier,
                contentDescription = contentDescription,
                contentScale = contentScale
            )
        }
        (data is Painter) -> {
            Image(
                painter = data,
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = contentScale
            )
        }
        else -> Unit
    }
}