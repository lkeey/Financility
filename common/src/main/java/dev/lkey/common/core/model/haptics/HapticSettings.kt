package dev.lkey.common.core.model.haptics

data class HapticSettings(
    val enabled: Boolean = true,
    val effect: HapticEffect = HapticEffect.CLICK
)
