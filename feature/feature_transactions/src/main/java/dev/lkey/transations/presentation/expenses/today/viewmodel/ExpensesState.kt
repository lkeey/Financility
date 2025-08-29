package dev.lkey.transations.presentation.expenses.today.viewmodel

import dev.lkey.common.core.model.account.AccountBriefModel
import dev.lkey.core.network.FinancilityResult
import dev.lkey.common.core.model.transaction.TransactionModel

/**
 * состояние экрана расходов
 * */

data class ExpensesState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
    val lastSync: Long? = null,
)
