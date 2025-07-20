package dev.lkey.settings.presentation.viewmodel

import dev.lkey.common.theme.ThemeMode

sealed interface SettingsEvent {

    data object OnLoadData: SettingsEvent
    data class OnChooseSyncDuration(val duration: Float): SettingsEvent
    data class OnChooseLanguage(val lang: String): SettingsEvent
    data class OnUpdateTheme(val theme: ThemeMode): SettingsEvent

}
