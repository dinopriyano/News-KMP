package com.dino.newskmp.platform

/**
 * Created by dinopriyano on 04/11/23.
 */

expect class DateTime() {

    fun getFormattedDate(
        iso8601Timestamp: String,
        format: String,
    ): String
}