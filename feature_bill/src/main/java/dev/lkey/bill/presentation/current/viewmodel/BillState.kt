package dev.lkey.bill.presentation.current.viewmodel

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.common.core.model.CurrencyOption
import dev.lkey.core.network.FinancilityResult

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
    val lastSync: Long? = null,
)
