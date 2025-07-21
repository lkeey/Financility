package dev.lkey.settings.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.common.core.model.AppInfo
import dev.lkey.core.network.FinancilityResult
import dev.lkey.settings.presentation.viewmodel.SettingsAction.ShowSnackBar
import dev.lkey.storage.data.encrypted.EncryptedStorage
import dev.lkey.storage.data.sync.AppSyncStorage
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM для экрана настроек
 * */

class SettingsViewModel @Inject constructor(
    private val appSyncStorage: AppSyncStorage,
    private val encryptedStorage: EncryptedStorage
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<SettingsAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: SettingsEvent
    ) {
        when (event) {
            is SettingsEvent.OnChooseSyncDuration -> {
                _state.update {
                    it.copy(
                        syncDuration = event.duration
                    )
                }

                appSyncStorage.setSyncDuration(event.duration.toLong())
                viewModelScope.launch {
                    _action.emit(ShowSnackBar("Интервал синхронизации успешно обновлен"))
                }
            }
            SettingsEvent.OnLoadData -> {
                val color = appSyncStorage.getPrimaryColor()
                _state.update {
                    it.copy(
                        syncDuration = appSyncStorage.getSyncDuration().toFloat(),
                        language = appSyncStorage.getSavedLocale(),
                        theme = appSyncStorage.getThemeMode(),
                        color = if (color != null) Color(color) else Color(0xFF2AE881),
                        appInfo = appSyncStorage.getAppInfo() ?: AppInfo("null", "null"),
                        status = FinancilityResult.Success
                    )
                }
            }
            is SettingsEvent.OnChooseLanguage -> {
                _state.update {
                    it.copy(
                        language = event.lang
                    )
                }

                appSyncStorage.setLocale(event.lang)

                viewModelScope.launch {
                    _action.emit(SettingsAction.RestartActivity)
                }
            }

            is SettingsEvent.OnUpdateTheme -> {
                _state.update {
                    it.copy(
                        theme = event.theme
                    )
                }

                appSyncStorage.saveThemeMode(event.theme)
                viewModelScope.launch {
                    _action.emit(SettingsAction.RestartActivity)
                }
            }

            is SettingsEvent.OnSetColor -> {
                _state.update {
                    it.copy(
                        color = event.color
                    )
                }

                appSyncStorage.savePrimaryColor(event.color.toArgb())

                viewModelScope.launch {
                    _action.emit(SettingsAction.RestartActivity)
                }
            }

            is SettingsEvent.OnEnterPin -> {
                if (state.value.stage == 0) {
                    _state.update {
                        it.copy(
                            firstEntry = event.pin
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            confirmEntry = event.pin
                        )
                    }
                }

                if ((state.value.stage == 0 && state.value.firstEntry.length == 4) || (state.value.stage == 1 && state.value.confirmEntry.length == 4)) {

                    if (state.value.stage == 0) {
                        _state.update {
                            it.copy(
                                stage = 1
                            )
                        }
                    } else {
                        if (state.value.firstEntry == state.value.confirmEntry) {
                            encryptedStorage.savePin(state.value.confirmEntry)
                            viewModelScope.launch {
                                _action.emit(SettingsAction.OnOpenSettingsScreen)
                                _action.emit(ShowSnackBar("PIN установлен"))
                            }

                        } else {
                            viewModelScope.launch {
                                _action.emit(ShowSnackBar("PIN не совпадает"))
                            }

                            _state.update {
                                it.copy(
                                    firstEntry = "",
                                    confirmEntry = "",
                                    stage = 0
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
