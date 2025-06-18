package dev.lkey.financility.feature_expenses.presentation.today

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ExpensesState (
    val transactions: List<TransactionModel> = listOf(),
    val accounts: List<AccountBriefModel> = listOf(),
    val isLoading: Boolean = false,
    val startDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE),
    val endDate: String = LocalDate.now()
        .format(DateTimeFormatter.ISO_DATE)
)
