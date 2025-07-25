package dev.lkey.transactions.domain

import dev.lkey.common.core.model.transaction.TransactionModel

interface TransactionsDatasource {

    suspend fun getTransactions(
        accountId : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>>

    suspend fun uploadUnsyncedTransactions(): Result<Unit>

}