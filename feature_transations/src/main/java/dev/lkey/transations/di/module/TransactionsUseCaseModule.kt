package dev.lkey.transations.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.transations.domain.repository.TransactionsRepository
import dev.lkey.transations.domain.usecase.DeleteTransactionUseCase
import dev.lkey.transations.domain.usecase.GetAccountUseCase
import dev.lkey.transations.domain.usecase.GetTransactionsUseCase
import dev.lkey.transations.domain.usecase.PostTransactionUseCase
import dev.lkey.transations.domain.usecase.UpdateTransactionUseCase

/**
 * UseCase зависимости, инициализируемые Dagger-ом
 * */

@Module
class TransactionsUseCaseModule {
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

    @Provides
    fun provideUpdateTransactionUseCase(
        transactionsRepository: TransactionsRepository
    ): UpdateTransactionUseCase {
        return UpdateTransactionUseCase(transactionsRepository)
    }

    @Provides
    fun provideDeleteTransactionUseCase(
        transactionsRepository: TransactionsRepository
    ): DeleteTransactionUseCase {
        return DeleteTransactionUseCase(transactionsRepository)
    }


}
