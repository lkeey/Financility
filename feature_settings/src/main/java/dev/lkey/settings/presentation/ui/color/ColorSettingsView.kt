package dev.lkey.settings.presentation.ui.color

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsState

@Composable
fun ColorSettingsView (
    modifier: Modifier = Modifier,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {

    val availablePrimaryColors = listOf(
        Color(0xFF2AE881),
        Color(0xFF2979FF),
        Color(0xFFFF5722),
        Color(0xFFAB47BC),
        Color(0xFFFFC107)
    )

    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text("Выберите основной цвет")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            availablePrimaryColors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(color)
                        .border(
                            width = if (color == state.color) 3.dp else 1.dp,
                            color = if (color == state.color) Color.Black else Color.Gray,
                            shape = CircleShape
                        )
                        .clickable {
                            onEvent(SettingsEvent.OnSetColor(color))
                        }
                )
            }
        }
    }
}
