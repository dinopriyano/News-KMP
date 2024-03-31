package com.dino.newskmp.common.di.feature

import com.dino.newskmp.common.data.remote.repository.NewsRepositoryImpl
import com.dino.newskmp.common.data.remote.service.NewsService
import com.dino.newskmp.common.data.remote.service.impl.NewsServiceImpl
import com.dino.newskmp.common.domain.repository.NewsRepository
import com.dino.newskmp.common.domain.usecase.news.GetNewsUseCase
import io.ktor.client.HttpClient
import org.koin.dsl.module

fun newsDataModule() = getNewsDataModule()

private fun getNewsDataModule() = module {
    single { getNewsService(get()) }
    single { getNewsRepository(get()) }
    single { getNewsUseCase(get()) }
}

private fun getNewsService(httpClient: HttpClient): NewsService = NewsServiceImpl(httpClient)
private fun getNewsRepository(newsService: NewsService): NewsRepository = NewsRepositoryImpl(newsService)
private fun getNewsUseCase(newsRepository: NewsRepository): GetNewsUseCase = GetNewsUseCase(newsRepository)