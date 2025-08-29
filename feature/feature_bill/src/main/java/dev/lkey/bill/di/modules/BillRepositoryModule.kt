package dev.lkey.bill.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.account.data.AccountRepositoryImpl
import dev.lkey.account.domain.AccountRepository
import dev.lkey.bill.data.repository.BillRepositoryImpl
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transactions.data.TransactionsDatasourceImpl
import dev.lkey.transactions.domain.TransactionsDatasource

/**
 * Модуль репозиториев счета
 * */

@Module
class BillRepositoryModule {

    @Provides
    fun provideAccountRepository(
        accountDao: AccountDao,
        appSyncStorage: AppSyncStorage
    ): AccountRepository {
        return AccountRepositoryImpl(
            accountDao = accountDao,
            appSyncStorage = appSyncStorage
        )
    }

    @Provides
    fun provideTransactionsDatasource(
        transactionDao: TransactionDao,
        syncStorage: AppSyncStorage
    ): TransactionsDatasource {
        return TransactionsDatasourceImpl(
            transactionDao = transactionDao,
            appSyncStorage = syncStorage
        )
    }

    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }

}
