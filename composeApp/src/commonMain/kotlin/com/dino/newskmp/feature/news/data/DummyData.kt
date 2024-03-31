package com.dino.newskmp.feature.news.data

import news_kmp.composeapp.generated.resources.Res
import news_kmp.composeapp.generated.resources.avatar_five
import news_kmp.composeapp.generated.resources.avatar_four
import news_kmp.composeapp.generated.resources.avatar_one
import news_kmp.composeapp.generated.resources.avatar_six
import news_kmp.composeapp.generated.resources.avatar_three
import news_kmp.composeapp.generated.resources.avatar_two
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import kotlin.random.Random


/**
 * Created by dinopriyano on 11/11/23.
 */

@OptIn(ExperimentalResourceApi::class)
object DummyData {

    private val avatars = listOf(
        Res.drawable.avatar_one,
        Res.drawable.avatar_two,
        Res.drawable.avatar_three,
        Res.drawable.avatar_four,
        Res.drawable.avatar_five,
        Res.drawable.avatar_six
    )

    fun getRandomAvatar(): DrawableResource {
        return avatars[Random.nextInt(0, 5)]
    }
}