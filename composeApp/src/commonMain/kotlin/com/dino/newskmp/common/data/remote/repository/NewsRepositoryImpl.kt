package com.dino.newskmp.common.data.remote.repository

import com.dino.newskmp.common.data.remote.dto.NewsItemResponse
import com.dino.newskmp.common.data.remote.dto.WebResponse
import com.dino.newskmp.common.data.remote.service.NewsService
import com.dino.newskmp.common.domain.repository.NewsRepository

class NewsRepositoryImpl (
    private val newsService: NewsService
): NewsRepository {

    override suspend fun getTrendingNews(category: String?): WebResponse<NewsItemResponse> {
        return newsService.getTrendingNews(category)
    }

    override suspend fun getEverythingNews(
        category: String?,
        keyword: String?
    ): WebResponse<NewsItemResponse> {
        return newsService.getEverythingNews(category, keyword)
    }
}