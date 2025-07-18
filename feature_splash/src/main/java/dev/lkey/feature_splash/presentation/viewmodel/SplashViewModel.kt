package dev.lkey.feature_splash.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.FinancilityResult
import dev.lkey.feature_splash.domain.GetAccountsUseCase
import dev.lkey.feature_splash.domain.GetArticlesUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel @Inject constructor (
    private val accountUseCase : GetAccountsUseCase,
    private val articlesUseCase : GetArticlesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<FinancilityResult>(FinancilityResult.Loading)
    val state: StateFlow<FinancilityResult> = _state

    fun onEvent(
        event: SplashEvent
    ) {
        when (event) {
            SplashEvent.OnLoadData -> loadData()
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
                                        FinancilityResult.Success
                                    }
                                }
                                else {
                                    _state.update {
                                        FinancilityResult.Error
                                    }
                                }
                            }
                            .onFailure { err->
                                if (err is OfflineDataException) {
                                    _state.update {
                                        FinancilityResult.Success
                                    }
                                } else {
                                    _state.update {
                                        FinancilityResult.Error
                                    }
                                }
                            }
                    } else {
                        _state.update {
                            FinancilityResult.Error
                        }
                    }

                }
                .onFailure { err->
                    if (err is OfflineDataException) {
                        _state.update {
                            FinancilityResult.Success
                        }
                    } else {
                        _state.update {
                            FinancilityResult.Error
                        }
                    }
                }
        }
    }
}
