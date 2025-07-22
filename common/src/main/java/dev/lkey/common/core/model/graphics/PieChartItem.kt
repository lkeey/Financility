package dev.lkey.common.core.model.graphics

import androidx.compose.ui.graphics.Color

data class PieChartItem(
    val valuePercent: Float,
    val label: String,
    val color: Color
)
