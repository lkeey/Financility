package dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel

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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * VM экрана расходов
 * */

class ExpensesViewModel (
    private val accountsUseCase : GetAccountUseCase,
    private val transactionUseCase : GetTransactionsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<ExpensesAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: ExpensesEvent
    ) {
        when (event) {
            is ExpensesEvent.OnLoadTodayExpenses -> {
                loadData()
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
                    status = FinancilityResult.Loading
                )
            }

            println("FAPP load acc 3 ${state.value.accounts}")

            val result = transactionUseCase.invoke(
                id = id,
                startDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
                endDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE),
            )

            result
                .onSuccess { res ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Success,
                            transactions = res.filter { !it.categoryModel.isIncome }
                        )
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
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
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = accountsUseCase.invoke()

            println("FAPP load acc 2 $result")

            result
                .onSuccess { res ->
                    if (res.isNotEmpty()) {
                        _state.update {
                            it.copy(
                                accounts = res
                            )
                        }
                        println("FAPP load acc 2.1 ${_state.value.accounts}")
                        onSuccess(res[0].id)
                    } else {
                        _state.update {
                            it.copy(
                                status = FinancilityResult.Error
                            )
                        }

                        _action.emit(ExpensesAction.ShowSnackBar("Не удалось найти аккаунт"))
                    }
                }
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(ExpensesAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }
        }
    }
}
