package dev.lkey.financility.feature_bill.presentation.edit.viewmodel

import dev.lkey.common.core.model.CurrencyOption
import dev.lkey.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

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
