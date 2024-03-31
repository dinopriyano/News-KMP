package com.dino.newskmp.common.domain.usecase.news

import com.dino.newskmp.common.data.remote.Resource
import com.dino.newskmp.common.domain.model.News
import com.dino.newskmp.common.domain.model.Wrapper
import com.dino.newskmp.common.domain.model.toDomain
import com.dino.newskmp.common.domain.repository.NewsRepository
import com.dino.newskmp.common.domain.usecase.BaseUseCase

class GetNewsUseCase (
    private val newsRepository: NewsRepository
): BaseUseCase<Wrapper<News>, GetNewsUseCase.Params>() {

    override suspend fun run(params: Params): Resource<Wrapper<News>> {
        return safeApiCall {
            newsRepository.getTrendingNews(
                if (params.category.lowercase() != "trending") {
                    params.category
                } else {
                    null
                }
            ).toDomain { list ->
                list?.map { it.toDomain() }
            }
        }
    }

    data class Params(val category: String)
}