package com.dino.newskmp.feature.interest.di

import com.dino.newskmp.feature.interest.presentation.screen.manage_interest.ManageInterestScreenModel
import org.koin.dsl.module

fun interestModule() = getScreenModule()

private fun getScreenModule() = module {
    factory { ManageInterestScreenModel() }
}