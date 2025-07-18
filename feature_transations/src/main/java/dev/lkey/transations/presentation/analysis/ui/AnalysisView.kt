package dev.lkey.transations.presentation.analysis.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.common.R
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
) {

    val scrollState = rememberScrollState()

    val totalSum = state.transactions.sumOf { it.amount.toDouble() }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(
                state = scrollState,
            )
    ) {

        state.lastSync?.let {
            FinancilitySyncMessage(it)
        }

        FinancilityDayPicker(
            title = "Период: начало",
            previousValue = state.startDate,
            isChip = true,
        ) {
            onEvent(
                AnalysisEvent.OnChangedStartDate(
                    start = it,
                    isIncome = isIncome
                )
            )
        }

        FinancilityDayPicker(
            title = "Период: конец",
            previousValue = state.endDate,
            isChip = true,
        ) {
            onEvent(
                AnalysisEvent.OnChangedEndDate(
                    end = it,
                    isIncome = isIncome
                )
            )
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


        if (state.transactions.isNotEmpty()) {

            state.transactions
                .sortedBy {
                    it.createdAt
                }
                .groupBy {
                    it.categoryModel
                }
                .forEach {
                    val categorySum = it.value.sumOf { it.amount.toDouble() }
                    FinancilityListItem(
                        trailingIcon = R.drawable.ic_light_arrow,
                        emoji = it.key.emoji,
                        title = it.key.name,
//                    description = it.comment,
                        trailingText = "${(categorySum / totalSum * 100).toFormat()}%",
                        trailingSubText = "$categorySum ${state.accounts[0].currency.toEmoji()}",
                        height = 70.dp,
                        isClickable = false
                    )
                }
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "Не было осуществлено транзакций за выбранный период :(",
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.5.sp,
                color = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }

}
