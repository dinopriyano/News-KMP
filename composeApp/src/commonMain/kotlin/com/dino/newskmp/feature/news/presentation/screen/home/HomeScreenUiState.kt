package com.dino.newskmp.feature.news.presentation.screen.home

import com.dino.newskmp.common.data.remote.Resource
import com.dino.newskmp.common.domain.model.News

/**
 * Created by dinopriyano on 11/11/23.
 */
data class HomeScreenUiState(
    val isLoading: Boolean = false,
    val showToast: Boolean = false,
    val newsError: Resource.Error? = null,
    val isShouldScrollToFirstItem: Boolean = false,
    val news: List<News> = emptyList()
)