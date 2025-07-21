package dev.lkey.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import dev.lkey.common.theme.ThemeMode

sealed interface SettingsEvent {

    data object OnLoadData: SettingsEvent
    data class OnChooseSyncDuration(val duration: Float): SettingsEvent
    data class OnChooseLanguage(val lang: String): SettingsEvent
    data class OnUpdateTheme(val theme: ThemeMode): SettingsEvent
    data class OnSetColor(val color: Color): SettingsEvent
    data class OnEnterPin(val pin: String): SettingsEvent

}
