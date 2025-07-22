package dev.lkey.transations.presentation.analysis.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.common.R
import dev.lkey.common.core.model.graphics.PieChartItem
import dev.lkey.common.ui.field.FinancilityDayPicker
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
    val availablePrimaryColors = listOf(
        Color(0xFF2AE881),
        Color(0xFF2979FF),
        Color(0xFFFF5722),
        Color(0xFFAB47BC),
        Color(0xFFFFC107)
    )

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

        val grouped = state.transactions
            .groupBy { it.categoryModel }

        val totalSum = grouped.values.flatten().sumOf { it.amount.toDouble() }

        val categorySums = grouped.map { (category, items) ->
            category to items.sumOf { it.amount.toDouble() }
        }

        // Сортировка по убыванию
        val sorted = categorySums.sortedByDescending { it.second }

        // Топ-5 категорий
        val topCategories = sorted.take(5)

        // Остальные
        val otherCategories = sorted.drop(5)
        val otherSum = otherCategories.sumOf { it.second }

        // Цвета
        var index = 0
        val data = mutableListOf<PieChartItem>()

        topCategories.forEach { (category, sum) ->
            data.add(
                PieChartItem(
                    valuePercent = ((sum / totalSum) * 100f).toFloat(),
                    label = category.name,
                    color = availablePrimaryColors[index % availablePrimaryColors.size]
                )
            )
            index++
        }

        // Добавляем "Другое", если есть
        if (otherSum > 0) {
            data.add(
                PieChartItem(
                    valuePercent = ((otherSum / totalSum) * 100f).toFloat(),
                    label = "Другое",
                    color = Color.LightGray // или любой другой цвет
                )
            )
        }

        DrawCircleGraphWithIcon(
            data = data,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .size(250.dp)
        )

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
