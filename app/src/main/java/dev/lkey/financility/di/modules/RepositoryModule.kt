package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.transations.data.repository.TransactionsRepositoryImpl
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Repository зависимости, инициализируемые Dagger-ом
 * */

@Module
class RepositoryModule {


    // feature transactions

    @Provides
    fun provideTransactionsRepository(): TransactionsRepository {
        return TransactionsRepositoryImpl()
    }


}