package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.core.network.ApiException
import dev.lkey.financility.core.network.retryRequest
import dev.lkey.financility.feature_transactions.data.dto.TransactionDto
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.delay

class PostTransactionUseCase {
    private val repository: TransactionsRepository = TransactionsRepositoryImpl()

    suspend fun invoke (
        transaction: TransactionDto
    ): Result<Unit> {

        return retryRequest {
            repository.createExpense(transaction)
        }

    }
}
