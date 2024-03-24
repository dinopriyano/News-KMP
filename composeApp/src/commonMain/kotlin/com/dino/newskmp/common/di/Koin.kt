package com.dino.newskmp.common.di

import com.dino.newskmp.feature.bookmark.di.discoveryModule
import com.dino.newskmp.feature.discovery.di.bookmarkModule
import com.dino.newskmp.feature.news.di.newsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// Called by android
fun initKoin(
    enableNetworkLogs: Boolean = false,
    baseUrl: String,
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(
        listOf(
            newsModule(enableNetworkLogs, baseUrl),
            bookmarkModule(enableNetworkLogs, baseUrl),
            discoveryModule(enableNetworkLogs, baseUrl)
        )
    )
}

// Called by iOS, Desktop, etc
fun initKoin(baseUrl: String) = initKoin(enableNetworkLogs = true, baseUrl = baseUrl) {}