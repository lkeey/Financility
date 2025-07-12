package dev.lkey.transations.data.repository

import dev.lkey.common.constants.Constants
import dev.lkey.common.core.model.TransactionModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.mappers.transaction.toTransactionEntity
import dev.lkey.storage.data.mappers.transaction.toTransactionModel
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transations.data.dto.TransactionDto
import dev.lkey.transations.domain.repository.TransactionsRepository
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject

/**
 * Репозиторий, который выолняет функции
 * - получения транзакций
 * - получения счетов
 * - добавления транзакции
 * - обновления транзакций
 * - удаления транзакций
 * */

class TransactionsRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val appSyncStorage: AppSyncStorage
) : TransactionsRepository {

    override suspend fun getTransactions(
        accountId: Int,
        startDate: String,
        endDate: String,
    ): Result<List<TransactionModel>> {
        return safeCall {
            try {
                val response = ktorClient.get("transactions/account/${accountId}/period") {
                    url {
                        parameters.append("startDate", startDate)
                        parameters.append("endDate", endDate)
                    }
                }

                if (response.status != HttpStatusCode.OK) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                val transactions = response.body<List<TransactionModel>>()

                /* save to local DB */
                transactionDao.insertAll(transactions.map {
                    it.toTransactionEntity(
                        isSynced = true
                    )
                })

                /* save last sync */
                appSyncStorage.saveSyncTime(
                    feature = Constants.TRANSACTION_SYNC,
                    timestamp = System.currentTimeMillis()
                )

                return@safeCall transactions

            } catch (e: Exception) {

                print("financility error 3")

                /* get cashed transactions */
                val cached = transactionDao.getAll().map {
                    it.toTransactionModel()
                }

                /* if not cashed data */
                if (cached.isNotEmpty()) {
                    throw OfflineDataException(cached)
                }

                throw e
            }
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

            if (response.status != HttpStatusCode.NoContent) {
                throw ApiException("Ошибка API: ${response.status}")
            }

            response.body()
        }
    }

}
