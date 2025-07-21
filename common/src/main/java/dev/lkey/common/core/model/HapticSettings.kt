package dev.lkey.common.core.model

data class HapticSettings(
    val enabled: Boolean = true,
    val effect: HapticEffect = HapticEffect.CLICK
)
