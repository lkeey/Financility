package dev.lkey.transations.domain.repository

import dev.lkey.common.core.model.TransactionModel
import dev.lkey.transations.data.dto.RequestTransactionDto

interface TransactionsRepository {

    suspend fun getTransactions(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun createTransaction(
        transaction: RequestTransactionDto
    ): Result<Unit>

    suspend fun updateTransaction(
        id: Int,
        transaction: RequestTransactionDto
    ): Result<Unit>

    suspend fun deleteTransaction(
        id: Int
    ): Result<Unit>

    suspend fun uploadUnsyncedTransactions(): Result<Unit>

}