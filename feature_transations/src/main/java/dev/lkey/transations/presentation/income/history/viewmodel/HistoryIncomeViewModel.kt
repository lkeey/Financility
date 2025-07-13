package dev.lkey.transations.presentation.income.history.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.common.constants.Constants.TRANSACTION_SYNC
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.core.error.ErrorHandler
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.FinancilityResult
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transations.domain.usecase.GetAccountsUseCase
import dev.lkey.transations.domain.usecase.GetTransactionsUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM экрана истории доходов
 * */

class HistoryIncomeViewModel @Inject constructor(
    private val accountsUseCase : GetAccountsUseCase,
    private val transactionUseCase : GetTransactionsUseCase,
    private val appSyncStorage: AppSyncStorage
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryIncomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<HistoryIncomeAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: HistoryIncomeEvent
    ) {
        when (event) {
            is HistoryIncomeEvent.OnLoadIncomes -> {
                loadData()
            }

            is HistoryIncomeEvent.OnChangedEndDate -> {
                _state.update {
                    it.copy(
                        endDate = event.end
                    )
                }
                loadIncomes(state.value.accounts[0].id)
            }

            is HistoryIncomeEvent.OnChangedStartDate -> {
                _state.update {
                    it.copy(
                        startDate = event.start
                    )
                }
                loadIncomes(state.value.accounts[0].id)
            }
        }

    }

    private fun loadData() {
        loadAccounts {
            loadIncomes(it)
        }
    }

    private fun loadIncomes(
        id: Int
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = transactionUseCase.invoke(
                id = id,
                startDate = state.value.startDate,
                endDate = state.value.endDate
            )

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Success,
                            transactions = res.filter { it.categoryModel.isIncome }
                        )
                    }
                }
                .onFailure { err ->
                    if (err is OfflineDataException) {
                        val transactions = err.data as List<TransactionModel>

                        _state.update {
                            it.copy(
                                status = FinancilityResult.Success,
                                transactions = transactions.filter {
                                    it.categoryModel.isIncome
                                },
                                lastSync = appSyncStorage.getSyncTime(
                                    feature = TRANSACTION_SYNC,
                                )
                            )
                        }
                    } else {
                        _state.update {
                            it.copy(status = FinancilityResult.Error)
                        }

                        _action.emit(HistoryIncomeAction.ShowSnackBar(ErrorHandler().handleException(err)))
                    }

                }
        }
    }

    private fun loadAccounts(
        onSuccess : (Int) -> Unit,
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading,
                )
            }

            val result = accountsUseCase.invoke()

            result
                .onSuccess { res ->
                    if (res.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                accounts = res
                            )
                        }
                        onSuccess.invoke(res[0].id)
                    } else {
                        _state.update {
                            it.copy(
                                status = FinancilityResult.Error,
                            )
                        }

                        _action.emit(HistoryIncomeAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error,
                        )
                    }

                    _action.emit(HistoryIncomeAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }
}
