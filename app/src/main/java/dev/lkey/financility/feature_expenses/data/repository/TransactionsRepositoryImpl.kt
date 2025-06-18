package dev.lkey.financility.feature_expenses.data.repository

import dev.lkey.financility.BuildConfig
import dev.lkey.financility.core.network.ApiException
import dev.lkey.financility.core.network.ktorClient
import dev.lkey.financility.core.network.safeCall
import dev.lkey.financility.feature_bill.domain.model.AccountBriefModel
import dev.lkey.financility.feature_expenses.data.dto.TransactionDto
import dev.lkey.financility.feature_expenses.domain.model.TransactionModel
import dev.lkey.financility.feature_expenses.domain.repository.TransactionsRepository
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

class TransactionsRepositoryImpl : TransactionsRepository {

    override suspend fun getTodayExpenses(
        accountId: Int,
        startDate: String,
        endDate: String,
    ): Result<List<TransactionModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/transactions/account/${accountId}/period") {
                url {
                    parameters.append("startDate", "2025-01-01")
                    parameters.append("endDate", "2025-12-01")
                }
            }

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun getAccount(): Result<List<AccountBriefModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("${BuildConfig.BASE_URL}/accounts")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun createExpense(transaction: TransactionDto): Result<Unit> {
        return safeCall {
            val response: HttpResponse = ktorClient.post("${BuildConfig.BASE_URL}/transactions") {
                setBody(transaction)
            }

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }
}
