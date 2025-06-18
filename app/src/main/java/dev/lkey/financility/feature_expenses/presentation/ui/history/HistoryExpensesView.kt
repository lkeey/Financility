package dev.lkey.financility.feature_expenses.presentation.ui.history

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.financility.R
import dev.lkey.financility.components.FinancilityDayPicker
import dev.lkey.financility.components.FinancilityListItem
import dev.lkey.financility.feature_expenses.presentation.ExpensesEvent
import dev.lkey.financility.feature_expenses.presentation.ExpensesState

@Composable
fun HistoryExpensesView (
    modifier: Modifier = Modifier,
    state: ExpensesState,
    onEvent: (ExpensesEvent) -> Unit
) {

    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        FinancilityDayPicker (
            title = "Начало",
            previousValue = state.startDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(ExpensesEvent.OnChangedStartDate(it))
        }

        FinancilityDayPicker (
            title = "Конец",
            previousValue = state.startDate,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow
        ) {
            onEvent(ExpensesEvent.OnChangedEndDate(it))
        }

        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Сумма",
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${state.transactions.sumOf { it.amount.toDouble() }} ${state.accounts[0].currency}",
                isClickable = false,
                isShowDivider = false
            )
        }

        state.transactions.forEach {
            FinancilityListItem(
                trailingIcon = R.drawable.ic_light_arrow,
                emoji = it.categoryModel.emoji,
                title = it.categoryModel.name,
                description = it.comment,
                trailingText = it.amount,
                height = 70.dp
            ) {
                /* TODO */
            }
        }
    }
}
