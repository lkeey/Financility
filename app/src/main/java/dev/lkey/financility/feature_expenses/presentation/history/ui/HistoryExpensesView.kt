package dev.lkey.financility.feature_expenses.presentation.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityDayPicker
import dev.lkey.financility.components.FinancilityListItem
import dev.lkey.financility.core.converter.toEmoji
import dev.lkey.financility.feature_expenses.presentation.history.HistoryExpensesEvent
import dev.lkey.financility.feature_expenses.presentation.history.HistoryExpensesState

@Composable
fun HistoryExpensesView (
    modifier: Modifier = Modifier,
    state: HistoryExpensesState,
    onEvent: (HistoryExpensesEvent) -> Unit
) {

    val scrollState = rememberScrollState()

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ){

        FinancilityDayPicker (
            title = "Начало",
            previousValue = state.startDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryExpensesEvent.OnChangedStartDate(it))
        }

        FinancilityDayPicker (
            title = "Конец",
            previousValue = state.endDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(HistoryExpensesEvent.OnChangedEndDate(it))
        }

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Сумма",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${state.transactions.sumOf { it.amount.toDouble() }} ${state.accounts[0].currency.toEmoji()}",
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
                    trailingSubText = it.createdAt.split("T")[1].substring(0, 5),
                    trailingText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                    height = 70.dp
                ) {
                    /* TODO */
                }
        }
    }
}


