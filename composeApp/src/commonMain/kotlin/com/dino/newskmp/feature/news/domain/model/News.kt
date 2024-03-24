package com.dino.newskmp.feature.news.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class News(

    @SerialName("publishedAt")
	val publishedAt: String? = null,

    @SerialName("author")
	val author: String? = null,

    @SerialName("urlToImage")
	val urlToImage: String? = null,

    @SerialName("description")
	val description: String? = null,

    @SerialName("source")
	val source: Source? = null,

    @SerialName("title")
	val title: String? = null,

    @SerialName("url")
	val url: String? = null,

    @SerialName("content")
	val content: String? = null
)

@Serializable
data class Source(

    @SerialName("name")
	val name: String? = null,

    @SerialName("id")
	val id: String? = null
)

