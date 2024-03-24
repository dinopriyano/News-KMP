package com.dino.newskmp.platform

import kotlinx.datetime.*
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern

/**
 * Created by dinopriyano on 04/11/23.
 */

actual class DateTime {

    @OptIn(FormatStringsInDatetimeFormats::class)
    @Suppress("NewApi") actual fun getFormattedDate(
        iso8601Timestamp: String,
        format: String,
    ): String {
        val date = getDateFromIso8601Timestamp(iso8601Timestamp)
        val formatter = LocalDateTime.Format {
            byUnicodePattern(format)
        }
        return date.format(formatter)
    }

    @Suppress("NewApi") private fun getDateFromIso8601Timestamp(iso8601Timestamp: String): LocalDateTime {
        val instant = Instant.parse(iso8601Timestamp)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault())
    }
}