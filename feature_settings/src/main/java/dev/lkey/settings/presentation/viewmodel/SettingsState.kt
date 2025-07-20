package dev.lkey.settings.presentation.viewmodel

import dev.lkey.core.network.FinancilityResult

data class SettingsState (
    val syncDuration: Float = 0f,
    val status: FinancilityResult = FinancilityResult.Loading
)
