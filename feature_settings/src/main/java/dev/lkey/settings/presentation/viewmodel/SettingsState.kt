package dev.lkey.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import dev.lkey.common.core.model.versions.AppInfo
import dev.lkey.common.core.model.haptics.HapticSettings
import dev.lkey.common.ui.theme.ThemeMode
import dev.lkey.core.network.FinancilityResult

data class SettingsState (
    val syncDuration: Float = 0f,

    val language: String = "",

    val theme: ThemeMode = ThemeMode.SYSTEM,
    val color: Color = Color(0xFFFFC107),

    val appInfo: AppInfo = AppInfo("null", "null"),

    val firstEntry: String = "",
    val confirmEntry: String = "",
    val stage: Int = 0,

    val haptics: HapticSettings = HapticSettings(),

    val status: FinancilityResult = FinancilityResult.Loading
)
