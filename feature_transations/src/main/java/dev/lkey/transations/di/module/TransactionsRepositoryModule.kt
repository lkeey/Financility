package dev.lkey.transations.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.transations.data.repository.TransactionsRepositoryImpl
import dev.lkey.transations.domain.repository.TransactionsRepository

/**
 * Repository зависимости, инициализируемые Dagger-ом
 * */

@Module
class TransactionsRepositoryModule {

    @Provides
    fun provideTransactionsRepository(): TransactionsRepository {
        return TransactionsRepositoryImpl()
    }

}
