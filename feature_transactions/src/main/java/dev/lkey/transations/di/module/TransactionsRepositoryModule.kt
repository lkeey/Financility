package dev.lkey.transations.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.account.data.AccountRepositoryImpl
import dev.lkey.account.domain.AccountRepository
import dev.lkey.articles.data.ArticlesRepositoryImpl
import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.storage.data.dao.AccountDao
import dev.lkey.storage.data.dao.CategoryDao
import dev.lkey.storage.data.dao.TransactionDao
import dev.lkey.storage.data.sync.AppSyncStorage
import dev.lkey.transactions.data.TransactionsDatasourceImpl
import dev.lkey.transactions.domain.TransactionsDatasource
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
    fun provideArticlesRepository(
        categoryDao: CategoryDao,
        syncStorage: AppSyncStorage
    ): ArticlesRepository {
        return ArticlesRepositoryImpl(
            categoryDao = categoryDao,
            appSyncStorage = syncStorage
        )
    }

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
}
