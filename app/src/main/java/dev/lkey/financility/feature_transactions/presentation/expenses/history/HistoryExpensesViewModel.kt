package dev.lkey.financility.feature_transactions.presentation.expenses.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.network.ErrorHandler
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_transactions.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryExpensesViewModel (
    private val accountsUseCase : GetAccountUseCase,
    private val transactionUseCase: GetTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<HistoryExpensesAction>()
    val action = _action.asSharedFlow()


    fun onEvent(
        event: HistoryExpensesEvent
    ) {
        when (event) {
            is HistoryExpensesEvent.OnLoadExpenses -> {
                loadData()
            }

            is HistoryExpensesEvent.OnChangedEndDate -> {
                _state.update {
                    it.copy(
                        endDate = event.end
                    )
                }
                loadExpenses(state.value.accounts[0].id)
            }

            is HistoryExpensesEvent.OnChangedStartDate -> {
                _state.update {
                    it.copy(
                        startDate = event.start
                    )
                }
                loadExpenses(state.value.accounts[0].id)
            }
        }

    }

    private fun loadData() {
        loadAccounts {
            loadExpenses(it)
        }
    }

    private fun loadExpenses(
        id : Int
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
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error,
                        )
                    }

                    _action.emit(HistoryExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
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

                        _action.emit(HistoryExpensesAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error,
                        )
                    }

                    _action.emit(HistoryExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

}
