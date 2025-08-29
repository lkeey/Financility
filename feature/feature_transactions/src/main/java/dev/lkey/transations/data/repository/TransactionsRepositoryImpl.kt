package dev.lkey.transations.data.repository

import android.util.Log
import dev.lkey.core.error.ApiException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transactions.data.dto.RequestTransactionDto
import dev.lkey.transactions.data.dto.ResponseTransactionDto
import dev.lkey.transactions.data.mappers.RequesttoTransactionEntity
import dev.lkey.transactions.data.mappers.ResponsetoTransactionEntity
import dev.lkey.transations.domain.repository.TransactionsRepository
import io.ktor.client.call.body
import io.ktor.client.request.delete
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

    override suspend fun createTransaction(requestTransactionDto: RequestTransactionDto): Result<Unit> {
        return safeCall {

            try {
                val response: HttpResponse = ktorClient.post("transactions") {
                    setBody(requestTransactionDto)
                }

                if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                val transaction = response.body<ResponseTransactionDto>()

                /* save to local db */
                val entity = transaction.ResponsetoTransactionEntity(
                    isSynced = true
                )

                transactionDao.insert(entity)

                return@safeCall
            } catch (e: Exception) {

                /* добавляем в бд, но не помечаем как синхронизованный */
                val entity = requestTransactionDto.RequesttoTransactionEntity(
                    isSynced = false
                )

                Log.d("OfflineData", "добавляем в кэш $e $entity")

                transactionDao.insert(entity)
            }
        }
    }

    override suspend fun updateTransaction(
        id: Int,
        transaction: RequestTransactionDto
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

            /* delete from local */
            transactionDao.delete(id)

            response.body()
        }
    }

}
