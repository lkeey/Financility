package dev.lkey.financility.feature_bill.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_income.domain.usecase.GetIncomeUseCase
import dev.lkey.financility.feature_income.presentation.IncomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BillViewModel : ViewModel() {

    private val _state = MutableStateFlow(BillState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        _state.value
    )

    private val billUseCase = GetBillInfoUseCase()

    init {
        viewModelScope.launch {
            val bill = billUseCase.invoke()
            _state.update {
                it.copy(
                    bill = bill
                )
            }
        }
    }
}
