package com.dino.newskmp.designSystem.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.nexa_extra_light
import news_kmp.composeapp.generated.resources.nexa_heavy
import news_kmp.composeapp.generated.resources.nexa_regular
import news_kmp.composeapp.generated.resources.outfit_bold
import news_kmp.composeapp.generated.resources.outfit_medium
import news_kmp.composeapp.generated.resources.outfit_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getTypography(): Typography {

    val nexaFontFamily = FontFamily(
        Font(Res.font.nexa_extra_light, weight = FontWeight.ExtraLight),
        Font(Res.font.nexa_regular, weight = FontWeight.Normal),
        Font(Res.font.nexa_heavy, weight = FontWeight.Bold)
    )

    val outfitFontFamily = FontFamily(
        Font(Res.font.outfit_regular, weight = FontWeight.Normal),
        Font(Res.font.outfit_medium, weight = FontWeight.Medium),
        Font(Res.font.outfit_bold, weight = FontWeight.Bold)
    )

    return Typography(
        bodySmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            ),
        ),
        bodyLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        ),
        labelSmall = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        ),
        labelMedium = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        ),
        labelLarge = TextStyle(
            fontFamily = outfitFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        ),
        titleSmall = TextStyle(
            fontFamily = nexaFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        ),
        titleMedium = TextStyle(
            fontFamily = nexaFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        ),
        titleLarge = TextStyle(
            fontFamily = nexaFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeightStyle = LineHeightStyle(
                alignment = LineHeightStyle.Alignment.Center,
                trim = LineHeightStyle.Trim.None
            )
        )
    )
}