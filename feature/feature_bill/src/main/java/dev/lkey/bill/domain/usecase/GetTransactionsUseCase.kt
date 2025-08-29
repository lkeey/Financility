package dev.lkey.bill.domain.usecase

import dev.lkey.common.core.model.transaction.TransactionModel
import dev.lkey.core.network.retryRequest
import dev.lkey.transactions.domain.TransactionsDatasource

/**
 * Use Case для получения транзакций пользователя
 * */

class GetTransactionsUseCase (
    private val repository: TransactionsDatasource
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