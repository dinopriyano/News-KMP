package com.dino.newskmp.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.dino.newskmp.utils.getDeviceOrientation
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.copy
import kotlinx.cinterop.zeroValue
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.UIKit.*

private fun Color.toUIColor(): UIColor = UIColor(
    red = this.red.toDouble(),
    green = this.green.toDouble(),
    blue = this.blue.toDouble(),
    alpha = this.alpha.toDouble()
)

@OptIn(ExperimentalForeignApi::class)
@Composable
private fun statusBarView() = remember {
    val keyWindow: UIWindow? =
        UIApplication.sharedApplication.windows.firstOrNull { (it as? UIWindow)?.isKeyWindow() == true } as? UIWindow
    val tag =
        3848245L // https://stackoverflow.com/questions/56651245/how-to-change-the-status-bar-background-color-and-text-color-on-ios-13

    keyWindow?.viewWithTag(tag)?.let {
        it
    } ?: run {
        val height =
            keyWindow?.windowScene?.statusBarManager?.statusBarFrame ?: zeroValue<CGRect>()
        val statusBarView = UIView(frame = height)
        statusBarView.tag = tag
        statusBarView.layer.zPosition = 999999.0
        keyWindow?.addSubview(statusBarView)
        statusBarView
    }
}

@Composable
actual fun SystemBarColor(statusBarColor: Color, navigationBarColor: Color) {
    val statusBar = statusBarView()
    SideEffect {
        statusBar?.backgroundColor = statusBarColor.toUIColor()
        UINavigationBar.appearance().backgroundColor = navigationBarColor.toUIColor()
    }
}