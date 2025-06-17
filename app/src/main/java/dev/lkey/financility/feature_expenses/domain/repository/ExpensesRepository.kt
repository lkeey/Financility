package dev.lkey.financility.feature_expenses.domain.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel

interface ExpensesRepository {

    suspend fun getTodayExpenses(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun getAccount(): Result<List<AccountBriefModel>>

}