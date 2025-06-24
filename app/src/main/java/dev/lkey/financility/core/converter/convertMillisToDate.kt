package dev.lkey.financility.core.converter

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertMillisToDate(
    millis : Long
): String {
    return Instant
        .ofEpochMilli(millis)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(DateTimeFormatter.ISO_DATE)
}
