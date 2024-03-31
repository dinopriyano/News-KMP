package com.dino.newskmp.feature.news.di

import com.dino.newskmp.feature.news.presentation.screen.home.HomeScreenModel
import org.koin.dsl.module

fun newsModule() = getScreenModule()

private fun getScreenModule() = module {
    factory { HomeScreenModel(get()) }
}