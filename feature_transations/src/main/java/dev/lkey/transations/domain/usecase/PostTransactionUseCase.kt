package dev.lkey.transations.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.transations.data.remote.dto.TransactionDto
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Use Case для добавления транзакций
 * */

class PostTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        transaction: TransactionDto
    ): Result<Unit> {

        return retryRequest {
            repository.createTransaction(transaction)
        }

    }
}
