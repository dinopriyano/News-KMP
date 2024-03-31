package com.dino.newskmp.common.domain.model

import com.dino.newskmp.common.data.remote.dto.WebResponse

data class Wrapper<T> (
    val status: String? = null,
    val message: String? = null,
    val code: String? = null,
    val totalResults: Int? = null,
    val articles: List<T>? = null
)

fun <T, U> WebResponse<T>.toDomain(mapperProvider: (List<T>?) -> List<U>?) = Wrapper(
    status,
    message,
    code,
    totalResults,
    mapperProvider(this.articles)
)
