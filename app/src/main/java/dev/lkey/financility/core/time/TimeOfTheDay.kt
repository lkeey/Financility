package dev.lkey.financility.core.time

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.ZoneId

fun getStartAndEndOfToday(): Pair<Long, Long> {
    val today = LocalDate.now()

    val startOfDay = today.atStartOfDay()
    val endOfDay = today.atTime(23, 59, 59, 999_999_999)

    val zoneId = ZoneId.systemDefault()

    val startTimestamp = startOfDay.atZone(zoneId).toInstant().toEpochMilli()
    val endTimestamp = endOfDay.atZone(zoneId).toInstant().toEpochMilli()

    return startTimestamp to endTimestamp
}
