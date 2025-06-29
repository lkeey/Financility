package dev.lkey.financility.feature_bill.presentation.current.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.lkey.financility.R
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.components.sheet.FinancilityCurrencySheet
import dev.lkey.financility.core.converter.toEmoji
import dev.lkey.financility.feature_bill.domain.model.CurrencyOption
import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillEvent
import dev.lkey.financility.feature_bill.presentation.current.viewmodel.BillState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillView (
    modifier: Modifier = Modifier,
    state: BillState,
    onEvent: (BillEvent) -> Unit
) {

    var isSheetOpen by remember { mutableStateOf(false) }

    if (isSheetOpen) {
        FinancilityCurrencySheet(
            currencies = listOf(
                CurrencyOption("RUB", "₽", "Российский рубль ₽"),
                CurrencyOption("USD", "$", "Американский доллар $"),
                CurrencyOption("EUR", "€", "Евро")
            ),
            onCurrencyClicked = {
                onEvent(BillEvent.OnChoseCurrency(it))
            },
        ) {
            isSheetOpen = false
        }
    }

    Column (
        modifier = modifier
            .fillMaxSize()
    ){
        state.accounts.forEach {
            FinancilityListItem(
                emoji = "\uD83D\uDCB0",
                title = stringResource(R.string.current_bill),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${it.balance} ${it.currency.toEmoji()}",
                trailingIcon = R.drawable.ic_light_arrow,
                backgroundEmojiColor = Color.White
            ) {
                /* TODO */
            }

            FinancilityListItem(
                title = stringResource(R.string.finance),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.currency.toEmoji(),
                trailingIcon = R.drawable.ic_light_arrow,
                isShowDivider = false,
            ) {
                isSheetOpen = true
            }
        }
    }
}