package dev.lkey.bill.presentation.current.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.lkey.bill.presentation.current.viewmodel.BillEvent
import dev.lkey.bill.presentation.current.viewmodel.BillState
import dev.lkey.common.R
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.core.converter.toEmoji

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillView (
    modifier: Modifier = Modifier,
    state: BillState,
    onEvent: (BillEvent) -> Unit
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
