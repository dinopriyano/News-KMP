package com.dino.newskmp.feature.discovery.di

import com.dino.newskmp.feature.discovery.presentation.screen.search.SearchScreenModel
import org.koin.dsl.module

fun bookmarkModule() = getScreenModule()

private fun getScreenModule() = module {
    single { SearchScreenModel() }
}