package com.dino.newskmp.feature.news.presentation.screen.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.dino.newskmp.common.data.remote.Resource
import com.dino.newskmp.common.domain.model.News
import com.dino.newskmp.common.domain.model.Wrapper
import com.dino.newskmp.common.domain.usecase.news.GetNewsUseCase
import com.dino.newskmp.common.presentation.base.BaseScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by dinopriyano on 05/11/23.
 */
class HomeScreenModel(
    private val getNewsUseCase: GetNewsUseCase
) : BaseScreenModel<HomeScreenUiState, HomeScreenUiEffect>(HomeScreenUiState()),
    HomeScreenInteractionListener {

    override val viewModelScope: CoroutineScope = screenModelScope

    private val newsCategories = listOf(
        "Trending", "Business", "Entertainment", "Health", "Science", "Sports", "Technology"
    )
    private var selectedCategory = newsCategories[0]

    init {
        getNews(selectedCategory)
        getNewsCategories()
    }

    private fun getNewsCategories() {
        updateState {
            it.copy(newsCategories = newsCategories)
        }
    }

    private fun getNews(category: String) {
        updateState {
            it.copy(isLoading = true)
        }
        getNewsUseCase.invoke(
            GetNewsUseCase.Params(
                category
            )
        ) { flow ->
            viewModelScope.launch {
                flow.collectLatest { result ->
                    when (result) {
                        is Resource.Success<Wrapper<News>> -> {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    isShouldScrollToFirstItem = true,
                                    news = result.data.articles.orEmpty()
                                )
                            }
                        }
                        is Resource.Error -> {
                            updateState {
                                it.copy(
                                    isLoading = false,
                                    showToast = true,
                                    newsError = result
                                )
                            }
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    override fun onSelectedCategoryChanged(categoryIndex: Int) {
        val newSelectedCategory = newsCategories[categoryIndex]
        if (newSelectedCategory != selectedCategory) {
            selectedCategory = newSelectedCategory
            getNews(newSelectedCategory)
        }
    }

    override fun onScrollTFirstItem() {
        updateState {
            it.copy(isShouldScrollToFirstItem = false)
        }
    }

    override fun onActionMenuClick() {
        sendNewEffect(HomeScreenUiEffect.NavigateToManageInterest)
    }
}