package dev.lkey.common.ui.graphics

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.common.core.model.graphics.BarChartItem
import kotlin.math.abs

@Composable
fun BarChart(
    data: List<BarChartItem>,
    modifier: Modifier = Modifier,
    maxBarHeight: Dp
) {

    val scrollState = rememberScrollState()
    val maxAbsValue = data.maxOf { abs(it.value) }

    LaunchedEffect(data) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }

    Row(
        modifier = modifier
            .horizontalScroll(scrollState)
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement
            .spacedBy(12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { item ->
            val barHeight = abs(item.value / maxAbsValue)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                // Бар
                Box(
                    modifier = Modifier
                        .height(maxBarHeight * barHeight)
                        .width(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(item.color)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Подпись — внизу
                Text(
                    text = item.dateLabel,
                    fontSize = 10.sp
                )

            }
        }
    }
}
