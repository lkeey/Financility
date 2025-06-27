package dev.lkey.financility.core.converter

import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertDateToMillis(dateStr: String): Long {
    return LocalDate
        .parse(dateStr, DateTimeFormatter.ISO_DATE)
        .atStartOfDay(ZoneId.systemDefault())
        .plusDays(1)
        .toInstant()
        .toEpochMilli()

}
