package dev.lkey.financility.feature_expenses.presentation.history

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class HistoryExpensesState (
    val transactions: List<TransactionModel> = listOf(),
    val accounts: List<AccountBriefModel> = listOf(),
    val isLoading: Boolean = false,
    val startDate: String = LocalDate.now()
        .withDayOfMonth(1)
        .format(DateTimeFormatter.ISO_DATE),
    val endDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE)
)