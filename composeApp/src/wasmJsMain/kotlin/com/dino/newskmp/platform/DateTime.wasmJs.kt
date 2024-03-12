package com.dino.newskmp.platform

import kotlinx.datetime.internal.JSJoda.DateTimeFormatter
import kotlinx.datetime.internal.JSJoda.ZonedDateTime

/**
 * Created by dinopriyano on 04/11/23.
 */

actual class DateTime {

    @Suppress("NewApi") actual fun getFormattedDate(
        iso8601Timestamp: String,
        format: String,
    ): String {
        val date = getDateFromIso8601Timestamp(iso8601Timestamp)
        val formatter = DateTimeFormatter.ofPattern(format)
        return date.format(formatter)
    }

    @Suppress("NewApi") private fun getDateFromIso8601Timestamp(string: String): ZonedDateTime {
        return ZonedDateTime.parse(string)
    }
}