package com.dino.newskmp.common.config

/**
 * Created by dinopriyano on 12/03/24.
 */

enum class Platform {
    IOS, ANDROID, DESKTOP, WEB
}

enum class Locales(val value: String) {
    INDONESIAN(INDONESIAN_LANGUAGE), ENGLISH(ENGLISH_LANGUAGE)
}