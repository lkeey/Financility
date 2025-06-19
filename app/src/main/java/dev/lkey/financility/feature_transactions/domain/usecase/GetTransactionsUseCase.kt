package dev.lkey.financility.feature_transactions.domain.usecase

import dev.lkey.financility.core.network.ApiException
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.model.TransactionModel
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.delay

class GetTransactionsUseCase {

    private val repository: TransactionsRepository = TransactionsRepositoryImpl()

    suspend operator fun invoke(
        id : Int,
        startDate: String,
        endDate: String
    ): Result<List<TransactionModel>> {

        var attempt = 0
        val maxRetries = 3

        while (attempt < maxRetries) {
            try {
                return repository.getTodayExpenses(id, startDate, endDate)
            } catch (e: ServerResponseException) {
                if (e.response.status.value == 500) {
                    attempt++
                    if (attempt == maxRetries) throw e
                    delay(2000)
                } else {
                    throw e
                }
            } catch (e: Exception) {
                throw e
            }
        }

        throw ApiException("Не удалось выполнить запрос. Превышено количество попыток")
    }

}