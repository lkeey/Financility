package dev.lkey.transations.presentation.analysis.viewmodel

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
import dev.lkey.transations.presentation.expenses.history.viewmodel.HistoryExpensesState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM экрана анализа транзакций
 * */

class AnalysisViewModel @Inject constructor(
    private val accountsUseCase : GetAccountsUseCase,
    private val transactionUseCase: GetTransactionsUseCase,
    private val appSyncStorage: AppSyncStorage
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<AnalysisAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: AnalysisEvent
    ) {
        when (event) {
            is AnalysisEvent.OnChangedEndDate -> {
                _state.update {
                    it.copy(
                        endDate = event.end
                    )
                }

                loadExpenses(
                    id = state.value.accounts[0].id,
                    isIncome = event.isIncome
                )
            }

            is AnalysisEvent.OnChangedStartDate -> {
                _state.update {
                    it.copy(
                        startDate = event.start
                    )
                }
                loadExpenses(
                    id = state.value.accounts[0].id,
                    isIncome = event.isIncome
                )
            }

            is AnalysisEvent.OnLoadTransactions -> {
                loadData(
                    isIncome = event.isIncome
                )
            }
        }
    }

    private fun loadData(
        isIncome : Boolean
    ) {
        loadAccounts {
            loadExpenses(
                id = it,
                isIncome = isIncome
            )
        }
    }

    private fun loadExpenses(
        id : Int,
        isIncome : Boolean
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading,
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
                            transactions = res.filter {
                                !it.categoryModel.isIncome
                            }
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
                                    it.categoryModel.isIncome == isIncome
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

                        _action.emit(AnalysisAction.ShowSnackBar(ErrorHandler().handleException(err)))
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

                        _action.emit(AnalysisAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error,
                        )
                    }

                    _action.emit(AnalysisAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

}
