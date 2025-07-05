package dev.lkey.financility.feature_bill.presentation.edit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lkey.financility.core.converter.toCurrency
import dev.lkey.financility.core.error.ErrorHandler
import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.data.model.UpdateAccountDto
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.financility.feature_bill.domain.usecase.UpdateBillUseCase
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * VM для редактирование счетов
 * */

class EditBillViewModel @Inject constructor(
    private val billInfoUseCase : GetBillInfoUseCase,
    private val updateBillUseCase : UpdateBillUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditBillState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        _state.value
    )

    private val _action = MutableSharedFlow<EditBillAction>()
    val action = _action.asSharedFlow()

    fun onEvent(
        event: EditBillEvent
    ) {
        when (event) {
            is EditBillEvent.OnEnteredBillName -> {
                _state.update {
                    it.copy(
                        enteredName = event.name
                    )
                }
            }
            EditBillEvent.OnLoadBill -> {
                loadData()
            }
            EditBillEvent.OnSaveBill -> {
                updateBill(
                    accDto = UpdateAccountDto(
                        name = state.value.enteredName,
                        balance = state.value.enteredAmount,
                        currency = state.value.chosenCurrency.code,
                    )
                )
            }

            is EditBillEvent.OnChoseCurrency -> {
                _state.update {
                    it.copy(
                        chosenCurrency = event.currency
                    )
                }
            }

            is EditBillEvent.OnEnteredAmount -> {
                _state.update {
                    it.copy(
                        enteredAmount = event.amount
                    )
                }
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
                            status = FinancilityResult.Success,
                            enteredName = res[0].name,
                            chosenCurrency = res[0].currency.toCurrency(),
                            enteredAmount = res[0].balance
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            status = FinancilityResult.Error
                        )
                    }

                    _action.emit(EditBillAction.ShowSnackBar("Не удалось найти аккаунт"))
                }
            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinancilityResult.Error
                    )
                }

                _action.emit(EditBillAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }

    private fun updateBill(
        accDto : UpdateAccountDto
    ) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    status = FinancilityResult.Loading
                )
            }

            val result = updateBillUseCase.invoke(
                id = state.value.accounts[0].id,
                newBill = accDto
            )

            result.onSuccess { res ->
                _state.update {
                    it.copy(
                        accounts = listOf<AccountBriefModel>(res),
                        status = FinancilityResult.Success
                    )
                }

                _action.emit(EditBillAction.ShowSnackBar("Счет успешно обновлен"))

            }.onFailure { err ->
                _state.update {
                    it.copy(
                        status = FinancilityResult.Error
                    )
                }

                _action.emit(EditBillAction.ShowSnackBar(ErrorHandler().handleException(err)))
            }
        }
    }
}
