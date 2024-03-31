package com.dino.newskmp.common.domain.model

import com.dino.newskmp.common.data.remote.dto.NewsItemResponse
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
data class News(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: Source? = null,
    val title: String? = null,
    val url: String? = null,
    val content: String? = null
)

@OptIn(ExperimentalResourceApi::class)
fun NewsItemResponse.toDomain() = News(
    publishedAt,
    author,
    urlToImage,
    description,
    source?.toDomain(),
    title,
    url,
    content
)
