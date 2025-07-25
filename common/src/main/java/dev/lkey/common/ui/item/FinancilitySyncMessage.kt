package dev.lkey.common.ui.item

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.common.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun FinancilitySyncMessage (
    time : Long
) {

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

    val formatted = formatter.format(Instant.ofEpochMilli(time))

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = stringResource(R.string.offline_data_exception, formatted),
        color = MaterialTheme.colorScheme.surfaceContainer,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center
    )
}
