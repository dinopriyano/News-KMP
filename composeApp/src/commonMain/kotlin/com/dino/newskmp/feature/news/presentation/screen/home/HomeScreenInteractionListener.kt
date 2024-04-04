package com.dino.newskmp.feature.news.presentation.screen.home

import com.dino.newskmp.common.presentation.base.BaseInteractionListener


interface HomeScreenInteractionListener : BaseInteractionListener {
    fun onSelectedCategoryChanged(categoryIndex: Int)
    fun onScrollTFirstItem()
    fun onActionMenuClick()
}