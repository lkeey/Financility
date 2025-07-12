package dev.lkey.transations.domain.repository

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.transations.data.dto.TransactionDto
import dev.lkey.common.core.model.TransactionModel

interface TransactionsRepository {

    suspend fun getTransactions(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun getAccounts(): Result<List<AccountBriefModel>>

    suspend fun createTransaction(
        transaction: TransactionDto
    ): Result<Unit>

    suspend fun updateTransaction(
        id: Int,
        transaction: TransactionDto
    ): Result<Unit>

    suspend fun deleteTransaction(
        id: Int
    ): Result<Unit>

    suspend fun getArticles(): Result<List<CategoryModel>>

}