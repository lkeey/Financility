package dev.lkey.financility.feature_bill.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.lkey.financility.R
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.core.converter.toEmoji
import dev.lkey.financility.core.converter.toFormat
import dev.lkey.financility.feature_bill.presentation.BillState

@Composable
fun BillView (
    modifier: Modifier = Modifier,
    state: BillState
) {
    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        state.accounts.forEach {
            FinancilityListItem(
                emoji = "\uD83D\uDCB0",
                title = "Баланс",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${it.balance} ${it.currency.toEmoji()}",
                trailingIcon = R.drawable.ic_light_arrow,
                backgroundEmojiColor = Color.White
            ) {
                /* TODO */
            }

            FinancilityListItem(
                title = "Валюта",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.currency.toEmoji(),
                trailingIcon = R.drawable.ic_light_arrow,
                isShowDivider = false,
            ) {
                /* TODO */
            }
        }
    }
}