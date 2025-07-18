package dev.lkey.transations.di.module

import dagger.Module
import dagger.Provides
import dev.lkey.account.domain.AccountRepository
import dev.lkey.articles.domain.ArticlesRepository
import dev.lkey.transations.domain.repository.TransactionsRepository
import dev.lkey.transations.domain.usecase.DeleteTransactionUseCase
import dev.lkey.transations.domain.usecase.GetAccountsUseCase
import dev.lkey.transations.domain.usecase.GetArticlesUseCase
import dev.lkey.transations.domain.usecase.GetTransactionsUseCase
import dev.lkey.transations.domain.usecase.PostTransactionUseCase
import dev.lkey.transations.domain.usecase.UpdateTransactionUseCase

/**
 * Модуль use-case траназакций
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
        accountsRepository: AccountRepository
    ): GetAccountsUseCase {
        return GetAccountsUseCase(accountsRepository)
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

    @Provides
    fun provideGetArticlesUseCase(
        articlesRepository: ArticlesRepository
    ): GetArticlesUseCase {
        return GetArticlesUseCase(articlesRepository)
    }

}
