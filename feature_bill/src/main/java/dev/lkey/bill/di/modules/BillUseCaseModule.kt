package dev.lkey.bill.di.modules

import dagger.Module
import dagger.Provides
import dev.lkey.account.domain.AccountRepository
import dev.lkey.bill.domain.repository.BillRepository
import dev.lkey.bill.domain.usecase.GetBillInfoUseCase
import dev.lkey.bill.domain.usecase.UpdateBillUseCase
import dev.lkey.transations.domain.repository.TransactionsRepository
import dev.lkey.transations.domain.usecase.GetTransactionsUseCase

/**
 * Модуль use-case счета
 * */

@Module
class BillUseCaseModule {

    @Provides
    fun provideGetBillInfoUseCase(
        accountRepository: AccountRepository
    ): GetBillInfoUseCase {
        return GetBillInfoUseCase(accountRepository)
    }

    @Provides
    fun provideUpdateBillUseCase(
        billRepository: BillRepository
    ): UpdateBillUseCase {
        return UpdateBillUseCase(billRepository)
    }

    @Provides
    fun provideGetTransactionsUseCase(
        transactionsRepository: TransactionsRepository
    ): GetTransactionsUseCase {
        return GetTransactionsUseCase(transactionsRepository)
    }

}
