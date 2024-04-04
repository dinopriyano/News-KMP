package com.dino.newskmp.common.di

import com.dino.newskmp.common.di.feature.newsDataModule
import com.dino.newskmp.feature.bookmark.di.discoveryModule
import com.dino.newskmp.feature.discovery.di.bookmarkModule
import com.dino.newskmp.feature.interest.di.interestModule
import com.dino.newskmp.feature.news.di.newsModule
import com.dino.newskmp.platform.platformNetworkEngineModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

// Called by android
fun initKoin(
    enableNetworkLogs: Boolean = true,
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(
        listOf(
            platformNetworkEngineModule(),
            networkModule(enableNetworkLogs),
            newsDataModule(),
            newsModule(),
            bookmarkModule(),
            discoveryModule(),
            interestModule()
        )
    )
}

// Called by iOS, Desktop, etc
fun initKoin() = initKoin(enableNetworkLogs = true) {}