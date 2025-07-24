package dev.lkey.transations.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.transactions.data.dto.RequestTransactionDto
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Use Case для обновления транзакций
 * */

class UpdateTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        id: Int,
        transaction: RequestTransactionDto
    ): Result<Unit> {
        return retryRequest {
            repository.updateTransaction(
                id = id,
                transaction = transaction
            )
        }
    }
}
