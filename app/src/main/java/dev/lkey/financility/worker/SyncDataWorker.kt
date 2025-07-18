package dev.lkey.financility.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.lkey.account.data.AccountRepositoryImpl
import dev.lkey.articles.data.ArticlesRepositoryImpl
import dev.lkey.core.di.utils.CoreProvider
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.storage.di.DaggerDatabaseComponent
import dev.lkey.transations.data.repository.TransactionsRepositoryImpl
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Отправляет запросы в фоновом потоке
 * - данные кешируются внутри вызова, поэтому в worker достаточно только отправить запрос
 */


class SyncDataWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    companion object {
        private const val TAG = "SyncDataWorker"
    }

    private val provider = appContext as CoreProvider
    private val db = DaggerDatabaseComponent.factory().create(provider.coreComponent)
    private val syncer = AppSyncStorage(context = appContext)

    private val articlesRepository = ArticlesRepositoryImpl(
        categoryDao = db.categoryDao(),
        appSyncStorage = syncer
    )

    private val accountRepository = AccountRepositoryImpl(
        accountDao = db.accountDao(),
        appSyncStorage = syncer
    )

    private val transactionsRepository = TransactionsRepositoryImpl(
        transactionDao = db.transactionDao(),
        appSyncStorage = syncer
    )

    override suspend fun doWork(): Result {
        return try {
            Log.d(TAG, "Задача выполняется")

            articlesRepository
                .getArticles()
                .onFailure {
                    Log.d(TAG, "ошибка получения статей $it")

                    return Result.retry()
                }
                .onSuccess {
                    Log.d(TAG, "Новые статьи: $it")
                }

            accountRepository
                .getRemoteAccounts()
                .onFailure {
                    Log.d(TAG, "ошибка получения счета $it")

                    return Result.retry()
                }
                .onSuccess {
                    Log.d(TAG, "Новые счета: $it")

                    transactionsRepository
                        .getTransactions(
                            accountId = it[0].id,
                            startDate = LocalDate.now()
                                .withDayOfMonth(1)
                                .format(DateTimeFormatter.ISO_DATE),
                            endDate = LocalDate.now()
                                .format(DateTimeFormatter.ISO_DATE),
                        )
                        .onFailure {
                            Log.d(TAG, "ошибка получения транзакций $it")

                            return Result.retry()
                        }
                        .onSuccess {
                            Log.d(TAG, "Новые транзакции: $it")
                        }
                }

            Log.d(TAG, "Задача выполнена")

            Result.success()
        } catch (e: Exception) {
            Log.d(TAG, "ошибка $e")

            Result.retry()
        }
    }
}
