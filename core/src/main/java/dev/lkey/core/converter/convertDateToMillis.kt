package dev.lkey.core.converter

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToMillis(dateStr: String): Long {
    return LocalDate
        .parse(dateStr, DateTimeFormatter.ISO_DATE)
        .atStartOfDay(ZoneId.systemDefault())
        .plusDays(1)
        .toInstant()
        .toEpochMilli()

}
