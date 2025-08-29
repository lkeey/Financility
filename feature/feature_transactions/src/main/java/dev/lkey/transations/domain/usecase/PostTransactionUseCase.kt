package dev.lkey.transations.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.transactions.data.dto.RequestTransactionDto
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Use Case для добавления транзакций
 * */

class PostTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        transaction: RequestTransactionDto
    ): Result<Unit> {

        return retryRequest {
            repository.createTransaction(transaction)
        }

    }
}
