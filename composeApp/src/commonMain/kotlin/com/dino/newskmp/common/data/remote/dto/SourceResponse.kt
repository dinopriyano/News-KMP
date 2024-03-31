package com.dino.newskmp.common.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponse(

	@SerialName("name")
	val name: String? = null,

	@SerialName("id")
	val id: String? = null
)