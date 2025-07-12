package dev.lkey.transations.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transations.data.repository.TransactionsRepositoryImpl
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Модуль репозиториев траназакций
 * */

@Module
class TransactionsRepositoryModule {

    @Provides
    fun provideTransactionsRepository(
        transactionDao: TransactionDao,
        syncStorage: AppSyncStorage
    ): TransactionsRepository {
        return TransactionsRepositoryImpl(
            transactionDao = transactionDao,
            appSyncStorage = syncStorage
        )
    }

}
