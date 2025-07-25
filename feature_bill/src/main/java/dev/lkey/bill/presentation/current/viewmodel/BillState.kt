package dev.lkey.bill.presentation.current.viewmodel

import dev.lkey.common.core.model.account.AccountBriefModel
import dev.lkey.common.core.model.account.CurrencyOption
import dev.lkey.common.core.model.transaction.TransactionModel
import dev.lkey.core.network.FinancilityResult

/**
 * Состояние экрана счетов
 * */

data class BillState (
    val accounts : List<AccountBriefModel> = emptyList(),
    val transactions : List<TransactionModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val chosenCurrency: CurrencyOption? = null,
    val lastSync: Long? = null,
)
