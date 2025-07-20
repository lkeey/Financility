package dev.lkey.settings.presentation.viewmodel

sealed interface SettingsEvent {

    data object OnLoadData: SettingsEvent
    data class OnChooseSyncDuration(val duration: Float): SettingsEvent
    data class OnChooseLanguage(val lang: String): SettingsEvent

}
