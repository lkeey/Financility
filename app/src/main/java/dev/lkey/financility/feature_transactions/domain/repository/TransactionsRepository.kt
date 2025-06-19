package dev.lkey.financility.feature_transactions.domain.repository

import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_transactions.data.dto.TransactionDto
import dev.lkey.financility.feature_transactions.domain.model.TransactionModel

interface TransactionsRepository {

    suspend fun getTodayExpenses(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun getAccount(): Result<List<AccountBriefModel>>

    suspend fun createExpense(
        transaction: TransactionDto
    ): Result<Unit>

}