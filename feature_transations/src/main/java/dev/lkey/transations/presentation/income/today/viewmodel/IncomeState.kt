package dev.lkey.transations.presentation.income.today.viewmodel

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.network.FinancilityResult
import dev.lkey.common.core.model.TransactionModel

/**
 * Состояние экрана доходов
 * */

data class IncomeState (
    val transactions: List<TransactionModel> = emptyList(),
    val accounts: List<AccountBriefModel> = emptyList(),
    val status: FinancilityResult = FinancilityResult.Loading,
)