package dev.lkey.financility.feature_expenses.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.network.ErrorHandler
import dev.lkey.financility.feature_expenses.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_expenses.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryExpensesViewModel : ViewModel() {

    private val _state = MutableStateFlow(HistoryExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<HistoryExpensesAction>()
    val action = _action.asSharedFlow()

    private val transactionUseCase = GetTransactionsUseCase()
    private val accountsUseCase = GetAccountUseCase()

    fun onEvent(
        event: HistoryExpensesEvent
    ) {
        when (event) {
            is HistoryExpensesEvent.OnLoadTransactions -> {
                loadData()
            }

            is HistoryExpensesEvent.OnChangedEndDate -> {
                _state.update {
                    it.copy(
                        endDate = event.end
                    )
                }
                loadExpenses()
            }

            is HistoryExpensesEvent.OnChangedStartDate -> {
                _state.update {
                    it.copy(
                        startDate = event.start
                    )
                }
                loadExpenses()
            }
        }

    }

    private fun loadData() {
        loadAccounts {
            loadExpenses()
        }
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = transactionUseCase.invoke(
                id = state.value.accounts[0].id,
                startDate = state.value.startDate,
                endDate = state.value.endDate
            )

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            transactions = res.filter { !it.categoryModel.isIncome }
                        )
                    }

//                    _action.emit(ExpensesHistoryAction.ShowSnackBar("Было найдено - ${res.size}"))

                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }

                    _action.emit(HistoryExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

    private fun loadAccounts(
        onSuccess : () -> Unit,
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = accountsUseCase.invoke()

            result
                .onSuccess { res ->
                    if (res.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                accounts = res
                            )
                        }
                        onSuccess.invoke()
                    } else {
                        _state.update {
                            it.copy(
                                isLoading = false,
                            )
                        }
                        _action.emit(HistoryExpensesAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _action.emit(HistoryExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

}
