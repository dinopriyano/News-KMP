package com.dino.newskmp.common.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsItemResponse(

	@SerialName("publishedAt")
	val publishedAt: String? = null,

	@SerialName("author")
	val author: String? = null,

	@SerialName("urlToImage")
	val urlToImage: String? = null,

	@SerialName("description")
	val description: String? = null,

	@SerialName("source")
	val source: SourceResponse? = null,

	@SerialName("title")
	val title: String? = null,

	@SerialName("url")
	val url: String? = null,

	@SerialName("content")
	val content: String? = null
)