package dev.lkey.financility.feature_bill.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.error.ErrorHandler
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.data.model.UpdateAccountDto
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_bill.domain.usecase.UpdateBillUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.isNotEmpty

/**
 * VM для экрана счетов
 * */

class BillViewModel (
    private val billInfoUseCase : GetBillInfoUseCase,
    private val updateBillUseCase : UpdateBillUseCase
): ViewModel() {

    private val _state = MutableStateFlow(BillState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<BillAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: BillEvent
    ) {
        when (event) {
            is BillEvent.OnLoadBill -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = billInfoUseCase.invoke()

            result.onSuccess { res ->
                if (res.isNotEmpty()) {
                    _state.update {
                        it.copy(
                            accounts = res,
                            status = FinancilityResult.Success
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(BillAction.ShowSnackBar("Не удалось найти аккаунт"))
                }
            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinancilityResult.Error
                    )
                }

                _action.emit(BillAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }

    private fun updateBillCurrency() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = updateBillUseCase.invoke(
                id = state.value.accounts[0].id,
                newBill = UpdateAccountDto(
                    name = state.value.accounts[0].name,
                    balance = state.value.accounts[0].balance,
                    currency = state.value.chosenCurrency
                )
            )

            result.onSuccess { res ->
                _state.update {
                    it.copy(
                        accounts = listOf<AccountBriefModel>(res),
                        status = FinancilityResult.Success
                    )

                }
                }.onFailure { err ->
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(BillAction.ShowSnackBar(ErrorHandler().handleException(err)))
                }

        }
    }

}
