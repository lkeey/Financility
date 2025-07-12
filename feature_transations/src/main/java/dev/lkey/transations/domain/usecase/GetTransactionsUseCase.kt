package dev.lkey.transations.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.transations.domain.repository.TransactionsRepository

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