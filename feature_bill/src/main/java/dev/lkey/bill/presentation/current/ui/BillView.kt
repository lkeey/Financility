package dev.lkey.bill.presentation.current.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.bill.R
import dev.lkey.bill.presentation.current.viewmodel.BillState
import dev.lkey.common.core.model.graphics.BarChartItem
import dev.lkey.common.ui.graphics.BarChart
import dev.lkey.common.ui.item.FinancilityListItem
import dev.lkey.common.ui.item.FinancilitySyncMessage
import dev.lkey.core.converter.toEmoji

@Composable
fun BillView (
    modifier: Modifier = Modifier,
    state: BillState,
) {

    Column (
        modifier = modifier
            .fillMaxSize()
    ){

        state.lastSync?.let {
            FinancilitySyncMessage(it)
        }

        state.accounts.forEach {
            FinancilityListItem(
                emoji = "\uD83D\uDC7B",
                title = stringResource(R.string.bill),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.name,
                backgroundEmojiColor = Color.White,
                isClickable = false,
            )

            FinancilityListItem(
                emoji = "\uD83D\uDCB0",
                title = stringResource(R.string.balance),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = "${it.balance} ${it.currency.toEmoji()}",
                isClickable = false,
                backgroundEmojiColor = Color.White
            )

            FinancilityListItem(
                title = stringResource(R.string.currency),
                description = null,
                backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
                trailingText = it.currency.toEmoji(),
                isClickable = false,
                isShowDivider = false,
            )
        }

        val chartData = listOf(
            BarChartItem("01.02", -50f),
            BarChartItem("02.02", -30f),
            BarChartItem("03.02", 10f),
            BarChartItem("04.02", 20f),
            BarChartItem("05.02", -70f),
            BarChartItem("06.02", 50f),
            BarChartItem("07.02", 30f),
            BarChartItem("01.02", -50f),
            BarChartItem("02.02", -30f),
            BarChartItem("03.02", 10f),
            BarChartItem("04.02", 20f),
            BarChartItem("05.02", -70f),
            BarChartItem("06.02", 50f),
            BarChartItem("07.02", 30f),
            BarChartItem("01.02", -50f),
            BarChartItem("02.02", -30f),
            BarChartItem("03.02", 10f),
            BarChartItem("04.02", 20f),
            BarChartItem("05.02", -70f),
            BarChartItem("06.02", 50f),
            BarChartItem("07.02", 30f),
        )

        BarChart(
            data = chartData,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 24.dp)
        )
    }
}
