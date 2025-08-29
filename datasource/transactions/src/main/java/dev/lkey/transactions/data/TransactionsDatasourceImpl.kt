package dev.lkey.transactions.data

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
import dev.lkey.transactions.data.dto.ResponseTransactionDto
import dev.lkey.transactions.data.mappers.ResponsetoTransactionEntity
import dev.lkey.transactions.data.mappers.toTransactionDto
import dev.lkey.transactions.domain.TransactionsDatasource
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Репозиторий, который выолняет функции
 * - получения транзакций
 * */

class TransactionsDatasourceImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val appSyncStorage: AppSyncStorage
) : TransactionsDatasource {

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

                if (response.status != HttpStatusCode.Companion.OK) {
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
                            val transactionDateTimeMillis = LocalDate.parse(
                                it.transactionDate.split("T")[0],
                                DateTimeFormatter.ISO_LOCAL_DATE
                            )
                            val startDateTimeMillis =
                                LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE)
                            val endDateTimeMillis =
                                LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE)

                            startDateTimeMillis <= transactionDateTimeMillis && transactionDateTimeMillis <= endDateTimeMillis
                        }
                    )
                }

                throw e
            }
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

                if (response.status != HttpStatusCode.Companion.OK && response.status != HttpStatusCode.Companion.Created) {
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
