package dev.lkey.financility.feature_expenses.data.network

import dev.lkey.financility.feature_expenses.data.TransactionResponseModel

interface RemoteTransactionDataSource {

    suspend fun getTodayTransactions(

    ): List<TransactionResponseModel>

}