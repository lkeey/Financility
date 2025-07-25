package dev.lkey.common.ui.haptics

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.lkey.common.R
import dev.lkey.common.core.model.haptics.HapticEffect

@Composable
fun HapticEffect.toReadableLabel(): String = when (this) {
    HapticEffect.CLICK -> stringResource(R.string.click)
    HapticEffect.HEAVY_CLICK -> stringResource(R.string.power_click)
    HapticEffect.DOUBLE_CLICK -> stringResource(R.string.double_click)
    HapticEffect.TICK -> stringResource(R.string.tick_click)
    HapticEffect.CUSTOM_SHORT -> stringResource(R.string.quick_click)
}
