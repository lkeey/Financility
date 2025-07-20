package dev.lkey.settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.core.network.FinancilityResult
import dev.lkey.storage.data.sync.AppSyncStorage
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

/**
 * VM для экрана настроек
 * */

class SettingsViewModel @Inject constructor(
    private val appSyncStorage: AppSyncStorage
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
            }
            SettingsEvent.OnLoadData -> {
                _state.update {
                    it.copy(
                        syncDuration = appSyncStorage.getSyncDuration().toFloat(),
                        status = FinancilityResult.Success
                    )
                }
            }
        }
    }

}
