package dev.lkey.transations.presentation.analysis.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.common.R
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.common.ui.field.FinancilityDayPicker
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.common.ui.item.FinancilitySyncMessage
import dev.lkey.core.converter.toEmoji
import dev.lkey.core.converter.toFormat
import dev.lkey.transations.presentation.analysis.viewmodel.AnalysisEvent
import dev.lkey.transations.presentation.expenses.history.viewmodel.HistoryExpensesState

@Composable
fun AnalysisView (
    modifier: Modifier = Modifier,
    state: HistoryExpensesState,
    isIncome: Boolean,
    onEvent: (AnalysisEvent) -> Unit,
    onItemClick: (TransactionModel) -> Unit,
) {

    val scrollState = rememberScrollState()

    val totalSum = state.transactions.sumOf { it.amount.toDouble() }

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
            title = "Период: начало",
            previousValue = state.startDate,
            isChip = true,
        ) {
            onEvent(AnalysisEvent.OnChangedStartDate(
                start = it,
                isIncome = isIncome
            ))
        }

        FinancilityDayPicker (
            title = "Период: конец",
            previousValue = state.endDate,
            isChip = true,
        ) {
            onEvent(AnalysisEvent.OnChangedEndDate(
                end = it,
                isIncome = isIncome
            ))
        }


        if (state.accounts.isNotEmpty()) {
            FinancilityListItem(
                title = "Сумма",
                description = null,
                trailingText = buildString {
                    append(totalSum.toFormat())
                    append(" ")
                    append(state.accounts[0].currency.toEmoji())
                },
                isClickable = false,
                isShowDivider = true
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
                    trailingText = "${(it.amount.toDouble() / totalSum * 100).toFormat()}%",
                    trailingSubText = "${it.amount} ${state.accounts[0].currency.toEmoji()}",
                    height = 70.dp
                ) {
                    onItemClick(it)
                }
            }
    }

}
