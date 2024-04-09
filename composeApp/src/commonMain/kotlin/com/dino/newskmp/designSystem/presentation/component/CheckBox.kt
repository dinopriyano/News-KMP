package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.dino.newskmp.common.utils.animateScaled
import com.dino.newskmp.common.utils.noRippleClickable
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.ic_check
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/**
 * Created by dinopriyano on 05/04/24.
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RawrCheckBox(
    modifier: Modifier,
    isChecked: Boolean,
    label: String,
    onChecked: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy((-5).dp)
    ) {
        Box(
            modifier = Modifier
                .size(checkboxSize)
                .clip(CircleShape)
                .background(Color.White)
                .padding(6.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.background,
                    shape = CircleShape
                )
                .zIndex(8f)
                .noRippleClickable {
                    onChecked(!isChecked)
                }
        ) {
            if (isChecked) {
                Icon(
                    modifier = Modifier.fillMaxSize().clip(CircleShape).background(MaterialTheme.colorScheme.background).padding(12.dp).animateScaled(),
                    painter = painterResource(Res.drawable.ic_check),
                    tint = Color.White,
                    contentDescription = null
                )
            }
        }
        CurveShape(
            modifier = Modifier.height(32.dp).width(24.dp),
            curveHeight = 16.dp,
            color = Color.White
        )
        Box(
            modifier = Modifier
                .height(checkboxSize)
                .weight(1f)
                .clip(RoundedCornerShape(50.dp))
                .background(Color.White),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                style = MaterialTheme.typography.labelMedium,
                text = label,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

@Composable
fun CurveShape(
    modifier: Modifier,
    curveHeight: Dp,
    color: Color
) {
    Canvas(modifier = modifier) {
        val path = Path()
        path.cubicTo(
            0f, 0f, size.width / 2f, curveHeight.toPx(), size.width, 0f
        )
        path.lineTo(size.width, size.height)
        path.cubicTo(size.width, size.height, size.width / 2f, size.height - curveHeight.toPx(), 0f, size.height)
        path.lineTo(0f, 0f)
        drawPath(
            path = path,
            color = color,
            style = Fill
        )
    }
}

private val checkboxSize = 56.dp