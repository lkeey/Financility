package dev.lkey.financility.feature_bill

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.lkey.financility.R
import dev.lkey.financility.components.ListItem

@Composable
fun BillView (
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        ListItem(
            emoji = "\uD83D\uDCB0",
            title = "Баланс",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "-670 000 ₽",
            trailingIcon = R.drawable.ic_light_arrow,
            backgroundEmojiColor = Color.White
        )

        ListItem(
            title = "Валюта",
            description = null,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            trailingText = "₽",
            trailingIcon = R.drawable.ic_light_arrow,
            isShowDivider = false,
        )
    }
}