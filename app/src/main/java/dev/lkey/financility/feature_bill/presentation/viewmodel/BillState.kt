package dev.lkey.financility.feature_bill.presentation.viewmodel

import dev.lkey.financility.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_bill.domain.model.CurrencyOption

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
)
