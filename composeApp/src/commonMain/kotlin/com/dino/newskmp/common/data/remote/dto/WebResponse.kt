package com.dino.newskmp.common.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebResponse<T>(

	@SerialName("status")
	val status: String? = null,

	@SerialName("message")
	val message: String? = null,

	@SerialName("code")
	val code: String? = null,

	@SerialName("totalResults")
	val totalResults: Int? = null,

	@SerialName("articles")
	val articles: List<T>? = null
)