package dev.lkey.financility.feature_expenses.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.feature_expenses.domain.usecase.GetTransactionsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpensesViewModel : ViewModel() {

    private val _state = MutableStateFlow(ExpensesState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val transactionUseCase = GetTransactionsUseCase()

    init {
        viewModelScope.launch {
            val transactions = transactionUseCase.invoke()
            _state.update {
                it.copy(
                    transactions = transactions
                )
            }
        }
    }
}
