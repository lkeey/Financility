package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.model.TransactionModel
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository

/**
 * Use Case для получения транзакций пользователя
 * */

class GetTransactionsUseCase (
    private val repository: TransactionsRepository
) {

    suspend operator fun invoke(
        id : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>> {

        return retryRequest {
            repository.getTransactions(id, startDate, endDate)
        }

    }

}