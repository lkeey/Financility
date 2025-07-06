package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.transations.domain.repository.TransactionsRepository
import dev.lkey.transations.domain.usecase.GetAccountUseCase
import dev.lkey.transations.domain.usecase.GetTransactionsUseCase
import dev.lkey.transations.domain.usecase.PostTransactionUseCase

/**
 * UseCase зависимости, инициализируемые Dagger-ом
 * */

@Module
class UseCaseModule {


    // feature bill



    // feature transactions
    @Provides
    fun provideGetTransactionsUseCase(
        transactionsRepository: TransactionsRepository
    ): GetTransactionsUseCase {
        return GetTransactionsUseCase(transactionsRepository)
    }

    @Provides
    fun providePostTransactionUseCase(
        transactionsRepository: TransactionsRepository
    ): PostTransactionUseCase {
        return PostTransactionUseCase(transactionsRepository)
    }

    @Provides
    fun provideGetAccountUseCase(
        transactionsRepository: TransactionsRepository
    ): GetAccountUseCase {
        return GetAccountUseCase(transactionsRepository)
    }

}
