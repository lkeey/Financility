package dev.lkey.settings.presentation.ui.sync

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.lkey.settings.presentation.viewmodel.SettingsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SyncSettingsView (
    modifier: Modifier = Modifier,
    state: SettingsState
) {

    val sliderRange = 15f..120f
    val stepCount = 5
    val stepSize = (sliderRange.endInclusive - sliderRange.start) / stepCount

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = "Интервал синхронизации: ${state.syncDuration} минут"
        )

        Slider(
            state = SliderState(
                value = state.syncDuration,
                steps = stepCount,
                valueRange = sliderRange
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0..5) {
                val label = (sliderRange.start + stepSize * i).toInt()
                Text(text = "$label", fontSize = 12.sp)
            }
        }
    }

}
