package com.dino.newskmp.common.data.remote.service.impl

import com.dino.newskmp.common.data.remote.HttpRoutes
import com.dino.newskmp.common.data.remote.dto.NewsItemResponse
import com.dino.newskmp.common.data.remote.dto.WebResponse
import com.dino.newskmp.common.data.remote.service.NewsService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class NewsServiceImpl constructor(
    private val httpClient: HttpClient
): NewsService {
    override suspend fun getTrendingNews(category: String?): WebResponse<NewsItemResponse> {
        return httpClient.get {
            url(HttpRoutes.TRENDING_NEWS)
            if (!category.isNullOrEmpty()) {
                parameter("category", category)
            }
            parameter("country", "us")
            parameter("pageSize", 10)
        }.body()
    }

    override suspend fun getEverythingNews(
        category: String?, keyword: String?
    ): WebResponse<NewsItemResponse> {
        return httpClient.get {
            url(HttpRoutes.EVERYTHING_NEWS)
            parameter("pageSize", 10)
            if (!category.isNullOrEmpty()) {
                parameter("category", category)
            }
            if (!keyword.isNullOrEmpty()) {
                parameter("q", keyword)
            }
        }.body()
    }
}