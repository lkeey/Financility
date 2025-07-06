package dev.lkey.financility.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.bill.domain.usecase.UpdateBillUseCase
import dev.lkey.transations.domain.repository.TransactionsRepository
import dev.lkey.transations.domain.usecase.GetAccountUseCase
import dev.lkey.transations.domain.usecase.GetTransactionsUseCase
import dev.lkey.transations.domain.usecase.PostTransactionUseCase

/**
 * UseCase зависимости, инициализируемые Dagger-ом
 * */

@Module
class UseCaseModule {

    // feature articles
//    @Provides
//    fun provideArticlesUseCase(
//        articlesRepository: ArticlesRepository
//    ): GetArticlesUseCase {
//        return GetArticlesUseCase(articlesRepository)
//    }

    // feature bill
    @Provides
    fun provideGetBillInfoUseCase(
        billRepository: BillRepository
    ): GetBillInfoUseCase {
        return GetBillInfoUseCase(billRepository)
    }

    @Provides
    fun provideUpdateBillUseCase(
        billRepository: BillRepository
    ): UpdateBillUseCase {
        return UpdateBillUseCase(billRepository)
    }

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
