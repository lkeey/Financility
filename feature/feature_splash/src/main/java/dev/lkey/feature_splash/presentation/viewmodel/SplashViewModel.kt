package dev.lkey.feature_splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.FinancilityResult
import dev.lkey.feature_splash.domain.GetAccountsUseCase
import dev.lkey.feature_splash.domain.GetArticlesUseCase
import dev.lkey.feature_splash.presentation.viewmodel.SplashAction.OnOpenMainScreen
import dev.lkey.feature_splash.presentation.viewmodel.SplashAction.ShowSnackBar
import dev.lkey.storage.data.encrypted.EncryptedStorage
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel @Inject constructor (
    private val accountUseCase : GetAccountsUseCase,
    private val articlesUseCase : GetArticlesUseCase,
    private val encryptedStorage: EncryptedStorage
) : ViewModel() {

    private val _state = MutableStateFlow(SplashState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<SplashAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: SplashEvent
    ) {
        when (event) {
            SplashEvent.OnLoadData -> {
                loadData()
                loadPin()
            }
            is SplashEvent.OnEnterPin -> {
                _state.update {
                    it.copy(
                        pin = event.pin
                    )
                }

                if (state.value.pin.length == 4) {
                    if (encryptedStorage.getPin() == state.value.pin) {
                        viewModelScope.launch {
                            _action.emit(OnOpenMainScreen)
                        }
                    } else {
                        _state.update {
                            it.copy(
                                pin = ""
                            )
                        }
                        viewModelScope.launch {
                            _action.emit(ShowSnackBar("Incorrect PIN"))
                        }
                    }
                }
            }
        }
    }

    private fun loadPin() {
        _state.update {
            it.copy(
                isPin = encryptedStorage.hasPin()
            )
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            accountUseCase.invoke()
                .onSuccess {resAccounts->
                    if (resAccounts.isNotEmpty()) {
                        articlesUseCase.invoke()
                            .onSuccess {resArticles->
                                if (resArticles.isNotEmpty()) {
                                    _state.update {
                                        it.copy(
                                            status = FinancilityResult.Success
                                        )
                                    }
                                }
                                else {
                                    _state.update {
                                        it.copy(
                                            status = FinancilityResult.Error
                                        )
                                    }
                                }
                            }
                            .onFailure { err->
                                if (err is OfflineDataException) {
                                    _state.update {
                                        it.copy(
                                            status = FinancilityResult.Success
                                        )
                                    }
                                } else {
                                    _state.update {
                                        it.copy(
                                            status = FinancilityResult.Error
                                        )
                                    }
                                }
                            }
                    } else {
                        _state.update {
                            it.copy(
                                status = FinancilityResult.Error
                            )
                        }
                    }

                }
                .onFailure { err->
                    if (err is OfflineDataException) {
                        _state.update {
                            it.copy(
                                status = FinancilityResult.Success
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(
                                status = FinancilityResult.Error
                            )
                        }
                    }
                }
        }
    }
}
