package dev.lkey.settings.presentation.viewmodel


/**
 * Действия со стороны VM на экран настроек
 * */


sealed class SettingsAction {
    data class ShowSnackBar(val message: String) : SettingsAction()
    object RestartActivity : SettingsAction()
    object OnOpenSettingsScreen : SettingsAction()
}
