package com.dino.newskmp.common.utils

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

object MathUtils {

    fun lerp(
        start: Float,
        stop: Float,
        fraction: Float,
        ) = start + (stop - start) * fraction.coerceIn(0f, 1f)

    fun calculateRotatedHeight(originalWidth: Float, originalHeight: Float, angleDegrees: Float): Float {
        val angleRadians = angleDegrees * (PI / 180).toFloat()
        return (originalWidth * abs(sin(angleRadians))) + (originalHeight * abs(cos(angleRadians)))
    }

}

