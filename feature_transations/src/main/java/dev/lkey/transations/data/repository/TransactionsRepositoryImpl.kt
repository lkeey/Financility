package dev.lkey.transations.data.repository

import dev.lkey.common.core.model.AccountBriefModel
import dev.lkey.common.core.model.CategoryModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.transations.data.dto.TransactionDto
import dev.lkey.transations.domain.model.TransactionModel
import dev.lkey.transations.domain.repository.TransactionsRepository
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Репозиторий, который выолняет функции
 * - получения транзакций
 * - получения счетов
 * - добавления транзакции
 * - обновления транзакций
 * - удаления транзакций
 * */

class TransactionsRepositoryImpl : TransactionsRepository {

    override suspend fun getTransactions(
        accountId: Int,
        startDate: String,
        endDate: String,
    ): Result<List<TransactionModel>> {
        return safeCall {
            val response = ktorClient.get("transactions/account/${accountId}/period") {
                url {
                    parameters.append("startDate", startDate)
                    parameters.append("endDate", endDate)
                }
            }

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun getAccounts(): Result<List<AccountBriefModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("accounts")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun createTransaction(transaction: TransactionDto): Result<Unit> {
        return safeCall {
            val response: HttpResponse = ktorClient.post("transactions") {
                setBody(transaction)
            }

            if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun updateTransaction(
        id: Int,
        transaction: TransactionDto
    ): Result<Unit> {
        return safeCall {
            val response: HttpResponse = ktorClient.put("transactions/${id}") {
                setBody(transaction)
            }

            if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun deleteTransaction(
        id: Int
    ): Result<Unit> {
        return safeCall {
            val response: HttpResponse = ktorClient.delete("transactions/${id}")

            if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

    override suspend fun getArticles(): Result<List<CategoryModel>> {
        return safeCall {
            val response: HttpResponse = ktorClient.get("categories")

            if (response.status != HttpStatusCode.OK) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }
}
