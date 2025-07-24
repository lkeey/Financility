package dev.lkey.transations.presentation.analysis.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.common.R
import dev.lkey.common.core.model.graphics.BarChartItem
import dev.lkey.common.core.model.graphics.PieChartItem
import dev.lkey.common.core.model.transaction.TransactionModel
import dev.lkey.common.ui.btn.FinancilityButton
import dev.lkey.common.ui.field.FinancilityDayPicker
import dev.lkey.common.ui.graphics.BarChart
import dev.lkey.common.ui.graphics.DrawCircleGraphWithIcon
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
    var isPieChart by remember {
        mutableStateOf(true)
    }

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
            title = stringResource(dev.lkey.transations.R.string.pediod_start),
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
            title = stringResource(dev.lkey.transations.R.string.pediod_end),
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
                title = stringResource(dev.lkey.transations.R.string.sum),
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

            if (isPieChart) {
                DrawCircleGraphWithIcon(
                    data = getPieItems(state.transactions),
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .size(250.dp)
                )
            } else {
                BarChart(
                    data = getPieItems(state.transactions).map {
                        BarChartItem(
                            dateLabel = it.label,
                            value = it.valuePercent,
                            color = it.color
                        )
                    },
                    modifier = Modifier
                        .padding(vertical = 24.dp),
                    maxBarHeight = 300.dp
                )
            }

            FinancilityButton(
                text = "Сменить вид",
                backgroundColor = MaterialTheme.colorScheme.primary,
            ) {
                isPieChart = !isPieChart
            }

            HorizontalDivider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceDim,
            )

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

private fun getPieItems(
    transactions: List<TransactionModel>
) : List<PieChartItem> {
    val availablePrimaryColors = listOf(
        Color(0xFF2AE881),
        Color(0xFF2979FF),
        Color(0xFFFF5722),
        Color(0xFFAB47BC),
        Color(0xFFFFC107)
    )

    val grouped = transactions
        .groupBy { it.categoryModel }

    val totalSum = grouped.values.flatten().sumOf { it.amount.toDouble() }

    val categorySums = grouped.map { (category, items) ->
        val sum = items.sumOf { it.amount.toDouble() }
        val percent = (sum / totalSum) * 100f
        Triple(category, sum, percent)
    }

    // Отфильтровали по проценту
    val majorCategories = categorySums.filter { it.third > 5f }
    val otherCategories = categorySums.filter { it.third <= 5f }
    val otherSum = otherCategories.sumOf { it.second }

    var index = 0
    val data = mutableListOf<PieChartItem>()

    // Добавляем категории > 5%
    majorCategories.forEach { (category, sum, _) ->
        data.add(
            PieChartItem(
                valuePercent = (((sum / totalSum) * 100f).toFloat()),
                label = category.name,
                color = availablePrimaryColors[index % availablePrimaryColors.size]
            )
        )
        index++
    }

    // Добавляем "Другое", если есть что добавить
    if (otherSum > 0) {
        data.add(
            PieChartItem(
                valuePercent = (((otherSum / totalSum) * 100f).toFloat()),
                label = "Другое",
                color = Color.LightGray
            )
        )
    }

    return data
}
