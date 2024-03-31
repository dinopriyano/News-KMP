package com.dino.newskmp.feature.bookmark.di

import com.dino.newskmp.feature.bookmark.presentation.screen.bookmark.FavoriteScreenModel
import org.koin.dsl.module

fun discoveryModule() = getScreenModule()

private fun getScreenModule() = module {
    single { FavoriteScreenModel() }
}