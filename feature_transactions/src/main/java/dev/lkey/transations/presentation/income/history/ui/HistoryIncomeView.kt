package dev.lkey.transations.presentation.income.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.core.model.transaction.TransactionModel
import dev.lkey.common.ui.field.FinancilityDayPicker
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.common.ui.item.FinancilitySyncMessage
import dev.lkey.core.converter.toEmoji
import dev.lkey.core.converter.toFormat
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transations.presentation.income.history.viewmodel.HistoryIncomeEvent
import dev.lkey.transations.presentation.income.history.viewmodel.HistoryIncomeState

@Composable
fun HistoryIncomeView (
    modifier: Modifier = Modifier,
    state: HistoryIncomeState,
    onEvent: (HistoryIncomeEvent) -> Unit,
    onItemClick: (TransactionModel) -> Unit,
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val hapticSettings = AppSyncStorage(context).loadHaptics()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        state.lastSync?.let {
            FinancilitySyncMessage(it)
        }

        FinancilityDayPicker (
            title = "Начало",
            previousValue = state.startDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryIncomeEvent.OnChangedStartDate(it))
        }

        FinancilityDayPicker (
            title = "Конец",
            previousValue = state.endDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryIncomeEvent.OnChangedEndDate(it))
        }

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Сумма",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${state.transactions.sumOf { it.amount.toDouble() }.toFormat()} ${state.accounts[0].currency.toEmoji()}",
                isClickable = false,
                isShowDivider = false
            )
        }

        state.transactions
            .sortedBy {
                it.createdAt
            }
            .forEach {
                FinancilityListItem(
                    trailingIcon = R.drawable.ic_light_arrow,
                    emoji = it.categoryModel.emoji,
                    title = it.categoryModel.name,
                    description = it.comment,
                    trailingSubText = it.transactionDate.substring(0, endIndex = 16).replace("T", " "),
                    trailingText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                    height = 70.dp,
                    context = context,
                    hapticSettings = hapticSettings
                ) {
                    onItemClick(it)
                }
            }
    }
}
