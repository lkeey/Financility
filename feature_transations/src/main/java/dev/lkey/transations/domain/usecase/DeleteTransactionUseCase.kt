package dev.lkey.transations.domain.usecase

import dev.lkey.core.network.retryRequest
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Use Case для удаления транзакций
 * */

class DeleteTransactionUseCase (
    private val repository: TransactionsRepository
) {
    suspend fun invoke (
        id: Int,
    ): Result<Unit> {
        return retryRequest {
            repository.deleteTransaction(
                id = id,
            )
        }
    }
}
