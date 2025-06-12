package dev.lkey.financility.feature_income.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.feature_income.domain.usecase.GetIncomeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class IncomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(IncomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val transactionUseCase = GetIncomeUseCase()

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
