package dev.lkey.settings.presentation.viewmodel

import dev.lkey.common.theme.ThemeMode
import dev.lkey.core.network.FinancilityResult

data class SettingsState (
    val syncDuration: Float = 0f,
    val language: String = "",
    val theme: ThemeMode = ThemeMode.SYSTEM,
    val status: FinancilityResult = FinancilityResult.Loading
)
