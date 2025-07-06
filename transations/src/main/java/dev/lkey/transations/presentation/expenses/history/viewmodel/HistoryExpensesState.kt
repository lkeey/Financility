package dev.lkey.transations.presentation.expenses.history.viewmodel

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.core.network.FinancilityResult
import dev.lkey.transations.domain.model.TransactionModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Состояние экрана истории расходов
 * */

data class HistoryExpensesState (
    val transactions: List<TransactionModel> = listOf(),
    val accounts: List<AccountBriefModel> = listOf(),
    val status : FinancilityResult = FinancilityResult.Loading,
    val startDate: String = LocalDate.now()
        .withDayOfMonth(1)
        .format(DateTimeFormatter.ISO_DATE),
    val endDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE)
)
