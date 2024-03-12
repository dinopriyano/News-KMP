package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.dino.newskmp.utils.getDeviceOrientation
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.copy
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIView
import platform.UIKit.UIWindow

private fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble(),
    )

@OptIn(ExperimentalForeignApi::class) @Composable
actual fun SystemBarColor(statusBarColor: Color, navigationBarColor: Color) {
    val safeFrameSize = remember { mutableStateOf(0.0) }

    val window =
        (UIApplication.sharedApplication.windows.first() as UIWindow)
    // Getting safe area size
    window.safeAreaLayoutGuide.layoutFrame.copy {
        // Getting safe area size in case of landscape and portrait
        if (getDeviceOrientation().portrait) {
            safeFrameSize.value = origin.y
        } else {
            safeFrameSize.value = origin.x
        }
    }

    val statusBar = UIView(
        frame = CGRectMake(
            x = 0.0,
            y = 0.0,
            width = Double.MAX_VALUE,
            height = safeFrameSize.value
        )
    )

    statusBar.backgroundColor = statusBarColor.toUIColor()
    SideEffect {
        UIApplication.sharedApplication.keyWindow?.addSubview(statusBar)
    }
}