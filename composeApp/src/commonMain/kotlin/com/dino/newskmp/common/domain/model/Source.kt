package com.dino.newskmp.common.domain.model

import com.dino.newskmp.common.data.remote.dto.SourceResponse

data class Source(
    val name: String? = null,
    val id: String? = null
)

fun SourceResponse.toDomain() = Source(
    name, id
)
