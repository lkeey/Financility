package dev.lkey.financility.feature_expenses.domain.usecase

import dev.lkey.financility.core.ApiException
import dev.lkey.financility.feature_expenses.data.repository.ExpensesRepositoryImpl
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_expenses.domain.repository.ExpensesRepository
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.delay

class GetExpensesUseCase {

    private val repository: ExpensesRepository = ExpensesRepositoryImpl()

    suspend operator fun invoke(): Result<List<TransactionModel>> {

        var attempt = 0
        val maxRetries = 3

        while (attempt < maxRetries) {
            try {
                return repository.getTodayExpenses()
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