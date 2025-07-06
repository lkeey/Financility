package dev.lkey.financility.feature_transactions.presentation.expenses.today.viewmodel

import dev.lkey.core.network.FinancilityResult
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.domain.model.TransactionModel

/**
 * состояние экрана расходов
 * */

data class ExpensesState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
)
