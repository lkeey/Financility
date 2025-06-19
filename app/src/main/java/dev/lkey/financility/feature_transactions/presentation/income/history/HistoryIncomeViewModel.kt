package dev.lkey.financility.feature_transactions.presentation.income.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.network.ErrorHandler
import dev.lkey.financility.feature_transactions.domain.usecase.GetAccountUseCase
import dev.lkey.financility.feature_transactions.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HistoryIncomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HistoryIncomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<HistoryIncomeAction>()
    val action = _action.asSharedFlow()

    private val transactionUseCase = GetTransactionsUseCase()
    private val accountsUseCase = GetAccountUseCase()

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
                loadExpenses()
            }

            is HistoryIncomeEvent.OnChangedStartDate -> {
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
                            transactions = res.filter { it.categoryModel.isIncome }
                        )
                    }
                }
                .onFailure { err ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                        )
//                    }

                    _action.emit(HistoryIncomeAction.ShowSnackBar(ErrorHandler().handleException(err)))
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
//                        _state.update {
//                            it.copy(
//                                isLoading = false,
//                            )
//                        }

                        _action.emit(HistoryIncomeAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                        )
//                    }

                    _action.emit(HistoryIncomeAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }
}
