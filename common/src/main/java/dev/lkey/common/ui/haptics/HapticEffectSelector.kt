package dev.lkey.common.ui.haptics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lkey.common.core.model.haptics.HapticEffect

@Composable
fun HapticEffectSelector(
    selected: HapticEffect,
    onSelect: (HapticEffect) -> Unit
) {
    val effects = HapticEffect.entries.toTypedArray()

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(effects.size) { index ->
            val effect = effects[index]
            val isSelected = effect == selected

            Surface(
                onClick = { onSelect(effect) },
                shape = RoundedCornerShape(8.dp),
                tonalElevation = if (isSelected) 8.dp else 2.dp,
                color = if (isSelected)
                    MaterialTheme.colorScheme.primaryContainer
                else
                    MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = effect.toReadableLabel(),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


