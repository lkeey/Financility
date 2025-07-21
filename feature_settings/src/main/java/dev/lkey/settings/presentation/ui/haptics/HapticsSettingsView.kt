package dev.lkey.settings.presentation.ui.haptics

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.lkey.common.core.model.HapticSettings
import dev.lkey.common.haptics.HapticEffectSelector
import dev.lkey.common.haptics.performHaptic
import dev.lkey.settings.R
import dev.lkey.settings.presentation.viewmodel.SettingsEvent
import dev.lkey.settings.presentation.viewmodel.SettingsState


@Composable
fun HapticsSettingsView (
    modifier: Modifier = Modifier,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.vibration))

            Switch(
                checked = state.haptics.enabled,
                onCheckedChange = {
                    onEvent(SettingsEvent.OnToggleHaptics(it))
                }
            )
        }

        AnimatedVisibility(
            visible = state.haptics.enabled
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(stringResource(R.string.effect_vibration))

                HapticEffectSelector(
                    selected = state.haptics.effect,
                    onSelect = { effect ->

                        onEvent(SettingsEvent.OnSelectEffect(effect))

                        performHaptic(context, HapticSettings(enabled = true, effect = effect))

                    }
                )
            }
        }
    }
}