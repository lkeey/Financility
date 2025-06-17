package dev.lkey.financility.feature_expenses.presentation

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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExpensesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<ExpensesAction>()
    val action = _action.asSharedFlow()

    private val transactionUseCase = GetTransactionsUseCase()
    private val accountsUseCase = GetAccountUseCase()

    fun onEvent(
        event: ExpensesEvent
    ) {
        when (event) {
            ExpensesEvent.OnOpenCreateScreen -> {

            }

            is ExpensesEvent.OnLoadTransactions -> {
                _state.update {
                    it.copy(
                        startDate =
                            if (event.isToday) LocalDate.now()
                                .format(DateTimeFormatter.ISO_DATE)
                            else LocalDate.now()
                                .withDayOfMonth(1)
                                .format(DateTimeFormatter.ISO_DATE)
                    )
                }

                loadData()
            }
        }
    }

    private fun loadData() {
        loadAccounts {
            loadExpenses(
                id = it
            )
        }
    }

    private fun loadExpenses(
        id : Int
    ) {

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = transactionUseCase.invoke(
                id = id,
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
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }

                    _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }

    private fun loadAccounts(
        onSuccess : (Int) -> Unit,
    ) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = accountsUseCase.invoke()

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            accounts = res
                        )
                    }
                   onSuccess.invoke(res[0].id)
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                        )
                    }
                    _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }
}
