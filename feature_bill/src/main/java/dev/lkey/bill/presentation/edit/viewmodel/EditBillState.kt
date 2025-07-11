package dev.lkey.bill.presentation.edit.viewmodel

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.common.core.model.CurrencyOption
import dev.lkey.core.network.FinancilityResult

/**
 * Состояние экрана счетов
 * */


data class EditBillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val enteredName: String = "",
    val enteredAmount: String = "",
    val chosenCurrency: CurrencyOption = CurrencyOption("NONE", "-", "NONE"),
)
