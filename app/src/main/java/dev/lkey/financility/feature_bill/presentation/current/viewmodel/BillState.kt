package dev.lkey.financility.feature_bill.presentation.current.viewmodel

import dev.lkey.common.core.model.CurrencyOption
import dev.lkey.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
)
