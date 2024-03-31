package com.dino.newskmp.common.domain.repository

import com.dino.newskmp.common.data.remote.dto.NewsItemResponse
import com.dino.newskmp.common.data.remote.dto.WebResponse

interface NewsRepository {
    suspend fun getTrendingNews(category: String? = null): WebResponse<NewsItemResponse>
    suspend fun getEverythingNews(category: String? = null, keyword: String? = null): WebResponse<NewsItemResponse>
}