package dev.lkey.common.ui.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.common.core.model.graphics.PieChartItem
import kotlin.math.roundToInt

@Composable
fun DrawCircleGraphWithIcon(
    data: List<PieChartItem>,
    modifier: Modifier = Modifier,
) {
    val sweepAngles = data.map { it.valuePercent / 100f * 360f }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        Canvas(modifier = Modifier.fillMaxSize()) {

            var startAngle = -90f

            data.forEachIndexed { index, item ->
                drawArc(
                    color = item.color,
                    startAngle = startAngle,
                    sweepAngle = sweepAngles[index],
                    useCenter = false,
                    style = Stroke(width = 40f, cap = StrokeCap.Butt)
                )
                startAngle += sweepAngles[index]
            }
        }

        // Центральный блок со всеми категориями
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            data.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(item.color)
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = "${item.valuePercent.roundToInt()}% ${item.label}",
                        fontWeight = FontWeight(weight = 500),
                        lineHeight = 22.sp,
                        letterSpacing = 0.sp,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                    )
                }
            }
        }
    }
}