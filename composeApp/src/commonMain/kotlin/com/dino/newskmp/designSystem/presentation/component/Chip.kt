package com.dino.newskmp.designSystem.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

/**
 * Created by dinopriyano on 15/03/24.
 */

@Composable
fun RawrChip(
    text: String,
    type: ChipType,
    textColor: Color,
    containerColor: Color = MaterialTheme.colorScheme.background,
    modifier: Modifier,
    onClick: (String) -> Unit
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = textColor,
        modifier = modifier.clip(RoundedCornerShape(50))
            .then(
                when (type) {
                    ChipType.FILLED -> {
                        Modifier.background(containerColor)
                    }
                    ChipType.OUTLINED -> {
                        Modifier.border(1.dp, containerColor, RoundedCornerShape(50))
                    }
                    else -> {
                        Modifier
                    }
                }
            )
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable { onClick(text) }
    )
}

enum class ChipType {
    OUTLINED, FILLED, GHOST
}

@Composable
@Preview
fun OutlinedChipPreview() {
    RawrChip(
        "Trending",
        ChipType.OUTLINED,
        Color.Blue,
        Color.Blue,
        Modifier.wrapContentWidth(),
        {}
    )
}

@Composable
@Preview
fun FilledChipPreview() {
    RawrChip(
        "Trending",
        ChipType.FILLED,
        Color.White,
        Color.Blue,
        Modifier.wrapContentWidth(),
        {}
    )
}

@Composable
@Preview
fun GhostChipPreview() {
    RawrChip(
        text = "Trending",
        type = ChipType.FILLED,
        textColor = Color.Black,
        modifier = Modifier.wrapContentWidth(),
        onClick = {}
    )
}
