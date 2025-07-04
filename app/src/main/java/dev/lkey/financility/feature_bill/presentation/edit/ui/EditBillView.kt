package dev.lkey.financility.feature_bill.presentation.edit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.field.FinancilityEditText
import dev.lkey.financility.components.field.FinancilityNumTextField
import dev.lkey.financility.components.item.FinancilityListItem
import dev.lkey.financility.components.sheet.FinancilityCurrencySheet
import dev.lkey.financility.core.converter.toEmoji
import dev.lkey.financility.feature_bill.domain.model.CurrencyOption
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillEvent
import dev.lkey.financility.feature_bill.presentation.edit.viewmodel.EditBillState

@Composable
fun EditBillView (
    modifier: Modifier = Modifier,
    state: EditBillState,
    onEvent: (EditBillEvent) -> Unit
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
                onEvent(EditBillEvent.OnChoseCurrency(it))
            },
        ) {
            isSheetOpen = false
        }
    }

    Column (
        modifier = modifier
            .fillMaxSize()
    ) {
        state.accounts.forEach {
            FinancilityEditText(
                previousData = state.enteredName,
                label = it.name,
                backgroundColor = White,
                isShowTrailingIcon = false,
                isShowLeadingIcon = true,
            ) {
                onEvent(EditBillEvent.OnEnteredBillName(it))
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceDim,
            )

            FinancilityNumTextField (
                title = "Баланс",
                previousData = state.enteredAmount,
                backgroundColor = White,
            ) {
                onEvent(EditBillEvent.OnEnteredAmount(it))
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceDim,
            )

            FinancilityListItem(
                title = stringResource(R.string.finance),
                description = null,
                backgroundColor = White,
                trailingText = state.chosenCurrency.symbol,
                trailingIcon = R.drawable.ic_light_arrow,

                isShowDivider = false,
            ) {
                isSheetOpen = true
            }

        }
    }
}
