package dev.lkey.transations.data.repository

import android.util.Log
import dev.lkey.common.constants.Constants
import dev.lkey.common.core.model.transaction.TransactionModel
import dev.lkey.core.error.ApiException
import dev.lkey.core.error.OfflineDataException
import dev.lkey.core.network.ktorClient
import dev.lkey.core.network.safeCall
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.mappers.transaction.toTransactionEntity
import dev.lkey.storage.data.mappers.transaction.toTransactionModel
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transations.data.dto.RequestTransactionDto
import dev.lkey.transations.data.dto.ResponseTransactionDto
import dev.lkey.transations.data.mappers.RequesttoTransactionEntity
import dev.lkey.transations.data.mappers.ResponsetoTransactionEntity
import dev.lkey.transations.data.mappers.toTransactionDto
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
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
            /* отправляем несихронизованные на сервер */
            uploadUnsyncedTransactions()

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

                /* get cashed transactions */
                val cached = transactionDao.getAll()
                    .map {
                        it.toTransactionModel()
                    }

                /* if not cashed data */
                if (cached.isNotEmpty()) {
                    Log.d("OfflineData", "cached = $cached")

                    throw OfflineDataException(
                        cached.filter {
                            val transactionDateTimeMillis = LocalDate.parse(it.transactionDate.split("T")[0], DateTimeFormatter.ISO_LOCAL_DATE)
                            val startDateTimeMillis = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE)
                            val endDateTimeMillis = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE)

                            startDateTimeMillis <= transactionDateTimeMillis && transactionDateTimeMillis <= endDateTimeMillis
                        }
                    )
                }

                throw e
            }
        }
    }

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

    /* получаем все несинхронизованные транзакции и отправляем их на сервер */
    override suspend fun uploadUnsyncedTransactions(): Result<Unit> {
        return safeCall {
            val unsyncedTransactions = transactionDao.getUnsynced()

            Log.d("OfflineData", "1. unsynced cached = $unsyncedTransactions")

            if (unsyncedTransactions.isEmpty()) return@safeCall

            for (unsyncedTransaction in unsyncedTransactions) {

                val response: HttpResponse = ktorClient.post("transactions") {
                    setBody(unsyncedTransaction.toTransactionDto())
                }

                if (response.status != HttpStatusCode.OK && response.status != HttpStatusCode.Created) {
                    throw ApiException("Ошибка API: ${response.status}")
                }

                /* удаляем из локального хранилища */
                transactionDao.delete(unsyncedTransaction.id)

                val transaction = response.body<ResponseTransactionDto>()

                /* сохраняем с ID в локальное хранилище */
                val entity = transaction.ResponsetoTransactionEntity(
                    isSynced = true
                )

                transactionDao.insert(entity)
            }

            Log.d("OfflineData", "2. unsynced cached = ${transactionDao.getUnsynced()}")

        }

    }

}
