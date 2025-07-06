package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.articles.domain.repository.ArticlesRepository
import dev.lkey.articles.repository.ArticlesRepositoryImpl
import dev.lkey.financility.feature_bill.data.repository.BillRepositoryImpl
import dev.lkey.financility.feature_bill.domain.repository.BillRepository
import dev.lkey.financility.feature_transactions.data.repository.TransactionsRepositoryImpl
import dev.lkey.financility.feature_transactions.domain.repository.TransactionsRepository

/**
 * Repository зависимости, инициализируемые Dagger-ом
 * */

@Module
class RepositoryModule {

    // feature articles
    @Provides
    fun provideArticlesRepository(): ArticlesRepository {
        return ArticlesRepositoryImpl()
    }

    // feature bill
    @Provides
    fun provideBillRepository(): BillRepository {
        return BillRepositoryImpl()
    }

    // feature transactions

    @Provides
    fun provideTransactionsRepository(): TransactionsRepository {
        return TransactionsRepositoryImpl()
    }


}